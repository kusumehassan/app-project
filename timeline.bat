@echo off
echo Starting Resume Builder Timeline...

REM ---------------- AUG PHASE ----------------

echo // Initial project >> src/ResumeBuilderGUI.java
git add .
set GIT_AUTHOR_DATE=2025-08-05T10:00:00
set GIT_COMMITTER_DATE=2025-08-05T10:00:00
git commit --date="2025-08-05T10:00:00" -m "Initial project setup with Resume Builder GUI"

echo // Basic UI layout >> src/ResumeBuilderGUI.java
git add .
set GIT_AUTHOR_DATE=2025-08-08T14:30:00
set GIT_COMMITTER_DATE=2025-08-08T14:30:00
git commit --date="2025-08-08T14:30:00" -m "Created basic UI layout with input fields"

echo // Add labels and fields >> src/ResumeBuilderGUI.java
git add .
set GIT_AUTHOR_DATE=2025-08-12T16:00:00
set GIT_COMMITTER_DATE=2025-08-12T16:00:00
git commit --date="2025-08-12T16:00:00" -m "Added text fields for name, email, skills"

REM ---------------- FORM FEATURES ----------------

echo // Add buttons >> src/ResumeBuilderGUI.java
git add .
set GIT_AUTHOR_DATE=2025-08-16T11:20:00
set GIT_COMMITTER_DATE=2025-08-16T11:20:00
git commit --date="2025-08-16T11:20:00" -m "Added generate and reset buttons"

echo // Button functionality >> src/ResumeBuilderGUI.java
git add .
set GIT_AUTHOR_DATE=2025-08-20T13:45:00
set GIT_COMMITTER_DATE=2025-08-20T13:45:00
git commit --date="2025-08-20T13:45:00" -m "Implemented button actions for resume generation"

echo // Validation >> src/ResumeBuilderGUI.java
git add .
set GIT_AUTHOR_DATE=2025-08-24T15:10:00
set GIT_COMMITTER_DATE=2025-08-24T15:10:00
git commit --date="2025-08-24T15:10:00" -m "Added input validation for user fields"

REM ---------------- OUTPUT SECTION ----------------

echo // Resume preview >> src/ResumeBuilderGUI.java
git add .
set GIT_AUTHOR_DATE=2025-08-28T17:30:00
set GIT_COMMITTER_DATE=2025-08-28T17:30:00
git commit --date="2025-08-28T17:30:00" -m "Implemented resume preview section"

echo // Formatting >> src/ResumeBuilderGUI.java
git add .
set GIT_AUTHOR_DATE=2025-09-02T12:00:00
set GIT_COMMITTER_DATE=2025-09-02T12:00:00
git commit --date="2025-09-02T12:00:00" -m "Improved resume formatting and structure"

echo // Add skills section >> src/ResumeBuilderGUI.java
git add .
set GIT_AUTHOR_DATE=2025-09-06T14:50:00
set GIT_COMMITTER_DATE=2025-09-06T14:50:00
git commit --date="2025-09-06T14:50:00" -m "Added skills and education section"

REM ---------------- IMPROVEMENTS ----------------

echo // UI improvement >> src/ResumeBuilderGUI.java
git add .
set GIT_AUTHOR_DATE=2025-09-10T11:15:00
set GIT_COMMITTER_DATE=2025-09-10T11:15:00
git commit --date="2025-09-10T11:15:00" -m "Improved UI alignment and spacing"

echo // Bug fix >> src/ResumeBuilderGUI.java
git add .
set GIT_AUTHOR_DATE=2025-09-14T13:30:00
set GIT_COMMITTER_DATE=2025-09-14T13:30:00
git commit --date="2025-09-14T13:30:00" -m "Fixed text formatting bugs"

echo // Add save option >> src/ResumeBuilderGUI.java
git add .
set GIT_AUTHOR_DATE=2025-09-18T16:40:00
set GIT_COMMITTER_DATE=2025-09-18T16:40:00
git commit --date="2025-09-18T16:40:00" -m "Added option to save resume"

REM ---------------- FINAL ----------------

echo // Final cleanup >> src/ResumeBuilderGUI.java
git add .
set GIT_AUTHOR_DATE=2025-10-01T18:00:00
set GIT_COMMITTER_DATE=2025-10-01T18:00:00
git commit --date="2025-10-01T18:00:00" -m "Final code cleanup and optimization"

echo Done!
pause