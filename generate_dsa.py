import re
import os
import subprocess
import google.generativeai as genai

# File to store previously generated problem names
HISTORY_FILE = "generated_problems.txt"

# GitHub repository details
REPO_DIR = os.getcwd()
REMOTE_URL = "https://github.com/annuu1/DSA-Challenge-Java.git"  # Updated to your repo
BRANCH = "main"

# Load previously generated problems
def load_history():
    if os.path.exists(HISTORY_FILE):
        with open(HISTORY_FILE, "r") as file:
            return set(line.strip() for line in file if line.strip())
    return set()

# Save a new problem to history
def save_to_history(problem_name):
    with open(HISTORY_FILE, "a") as file:  # Back to append mode
        file.write(f"{problem_name}\n")

# List of common DSA problems (to guide the AI if needed)
dsa_problems = [
    "LinearSearch", "BinarySearch", "BubbleSort", "QuickSort", "MergeSort",
    "StackImplementation", "QueueImplementation", "LinkedList", "BinaryTreeTraversal",
    "GraphBFS", "GraphDFS"
]

# Git operations
def git_setup():
    if not os.path.exists(os.path.join(REPO_DIR, ".git")):
        subprocess.run(["git", "init"], cwd=REPO_DIR, check=True)
        subprocess.run(["git", "remote", "add", "origin", REMOTE_URL], cwd=REPO_DIR, check=True)
    if not os.path.exists(os.path.join(REPO_DIR, ".gitignore")):
        with open(os.path.join(REPO_DIR, ".gitignore"), "w") as f:
            f.write("*.class\n__pycache__\n")

def git_commit_and_push(filename):
    try:
        subprocess.run(["git", "add", filename], cwd=REPO_DIR, check=True)
        subprocess.run(["git", "add", HISTORY_FILE], cwd=REPO_DIR, check=True)
        subprocess.run(["git", "commit", "-m", f"Add {filename} from AI generation"], cwd=REPO_DIR, check=True)
        subprocess.run(["git", "push", "origin", BRANCH], cwd=REPO_DIR, check=True)
        print(f"Successfully pushed '{filename}' to GitHub repository.")
    except subprocess.CalledProcessError as e:
        print(f"Git operation failed: {e}")

# Configure the API with the key from environment
genai.configure(api_key=os.getenv("API_KEY"))

# Load history of generated problems
history = load_history()

# Craft a prompt for a new, unique DSA problem in Java
used_problems = ", ".join(history) if history else "none"
prompt = (
    f"Create a Java code for a data structures and algorithms (DSA) problem that hasn't been generated before. "
    f"Previously generated problems: {used_problems}. "
    f"Include a clear problem name in the response (e.g., as the public class name) and provide a complete, "
    f"working implementation with a main method to demonstrate it."
)

# Generate the response
model = genai.GenerativeModel("gemini-1.5-flash")  # Updated model name
response = model.generate_content(prompt)
full_text = response.text

# Step 1: Extract the Java code
code_pattern = r"```java\n(.*?)\n```"
code_match = re.search(code_pattern, full_text, re.DOTALL)
if code_match:
    code = code_match.group(1).strip()
else:
    print("No Java code block found in the response.")
    exit()

# Step 2: Extract the problem name (from class name)
class_pattern = r"public class (\w+)"
name_match = re.search(class_pattern, full_text)
if name_match:
    problem_name = name_match.group(1)
else:
    print("No public class found; using fallback name.")
    problem_name = "UnknownProblem"

# Check if this problem was already generated
if problem_name in history:
    print(f"Warning: '{problem_name}' was already generated. AI may not have followed uniqueness request.")
filename = f"{problem_name}.java"

# Step 3: Save the code to a .java file
try:
    with open(filename, "w") as file:
        file.write(code)
    print(f"Java code saved locally as '{filename}'")
    if problem_name not in history:
        save_to_history(problem_name)
        history.add(problem_name)
except Exception as e:
    print(f"Error saving file: {e}")
    exit()

# Step 4: Set up Git and push to GitHub
git_setup()
git_commit_and_push(filename)

# Step 5: Print what we extracted for verification
print("\nProblem Name:", problem_name)
print("Extracted Java Code:\n", code)
print(f"Previously Generated Problems: {', '.join(history)}")
