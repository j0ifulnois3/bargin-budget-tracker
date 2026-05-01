Joi's Business Budget Book
Capstone 1: Accounting Ledger Application

Application Setup
Open Project: Load the project folder in IntelliJ IDEA.

File Placement: Ensure transactions.csv is located in the root directory (the main project folder) so the loadTransactions method can access the data.

Execution: Run the NewApp.java class to begin the session.

Functional Overview
Main Menu
D (Deposit): Prompts for date, time, description, vendor, and amount. Saves the entry to the CSV file.

P (Payment): Prompts for details and automatically converts the amount to a negative value before saving.

L (Ledger): Navigates to the Ledger Menu.

X (Exit): Closes the application.

Ledger Menu
A (All): Displays every transaction in the system.

D (Deposits): Displays only entries with a positive value.

P (Payments): Displays only entries with a negative value.

R (Reports): Navigates to the Reports Sub-Menu.

H (Home): Returns to the Main Menu.

Reports Menu
1 - 4: Filters transactions by specific date ranges (Month to Date, Previous Month, Year to Date, or Previous Year).

5 (Search by Vendor): Prompts for a vendor name and displays all matching transactions regardless of case sensitivity.

0 (Back): Returns to the Ledger Menu.

Technical Implementation Notes
Sorting: All ledger views utilize a reverse for-loop to ensure the most recent transactions appear at the top of the list.

Formatting: Currency is displayed using formatted strings to ensure two decimal places are consistently shown.

Data Integrity: The application includes logic to skip empty or malformed lines within the CSV file during the loading process.

Final Steps
Create a new file in your project root named README.md.

Paste the text above into the file.

Save the file and push the changes to your GitHub repository before your presentation tomorrow.
