# Web-Automation
Web test automation
A robust and scalable Web Automation project built with **Java**, leveraging **BDD (Cucumber)**, **Microsoft Playwright** for browser automation, and **ExtentReports** for detailed test reporting.

## 🔧 Tech Stack

- **Java 17+**
- **Maven**
- **Cucumber (BDD)**
- **Playwright (Java bindings)**
- **TestNG**
- **ExtentReports**
- **Commons IO**

---

## 📁 Project Structure

Web-Automation/
├── src/
│ ├── main/
│ │ └── java/
│ └── test/
│ ├── java/
│ │ ├── stepdefinitions/
│ │ ├── runners/
│ │ ├── pages/
│ │ └── utils/
│ └── resources/
│ └── features/
├── reports/
├── pom.xml
└── README.md
---
## ✅ Features

- ✅ **Cross-browser automation** powered by Playwright
- ✅ **Readable and maintainable BDD scenarios** using Gherkin syntax
- ✅ **Parallel test execution** with TestNG
- ✅ **Detailed HTML reports** with screenshots using ExtentReports
- ✅ Modular and extensible design with Page Object Model (POM)
- ✅ Utility functions for common web actions and waits

---
## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone git@github.com:solly-mathebula/Web-Automation.git
cd Web-Automation

mvn clean install

mvn test