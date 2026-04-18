# 🚀 LLM-Powered Code Review Assistant (RAG-Based)

An intelligent full-stack web application that performs automated code reviews using a **Retrieval-Augmented Generation (RAG)** pipeline and a **pretrained LLM via Groq API**.

---

## 🧠 Overview

This project analyzes source code and provides structured feedback including:

* 🐞 Bugs & logical errors
* 🔐 Security vulnerabilities
* ⚡ Performance issues
* 🧹 Code quality improvements
* 💡 Detailed explanations & fixes

The system combines **LLM reasoning** with **rule-based retrieval** to improve accuracy and reduce hallucinations.

---

## ⚙️ Tech Stack

### Backend

* Java + Spring Boot
* REST APIs
* Layered architecture

### Frontend

* React.js
* Tailwind CSS
* Axios

### AI / LLM

* Groq API (pretrained LLM)

### RAG (Retrieval Layer)

* Rule-based context injection (upgradeable to vector DB)

---

## 🧩 Architecture

```
User Input (Code)
        ↓
Frontend (React UI)
        ↓
Spring Boot API
        ↓
RAG Layer (rules + context retrieval)
        ↓
LLM (via Groq API)
        ↓
Structured JSON Review
        ↓
Frontend Visualization
```

---

## 🔍 Features

* 📌 Paste and review code instantly
* 📊 Categorized issue detection
* 🚨 Severity-based classification (HIGH / MEDIUM / LOW)
* 🧠 Context-aware analysis using RAG
* 🧾 Structured JSON responses
* 💬 Detailed explanations for each issue

---

## 🛠️ How It Works

1. User submits code through UI
2. Backend retrieves relevant coding rules
3. A structured prompt is generated
4. Groq LLM analyzes the code
5. Response is parsed into structured format
6. Results are displayed in the frontend

---

## 🔐 Example Issue Detection

```json
{
  "security": [
    {
      "issue": "Hardcoded API key detected",
      "severity": "HIGH",
      "fix": "Use environment variables instead",
      "explanation": "Hardcoding credentials can expose sensitive data"
    }
  ]
}
```

---

## ⚠️ Important Notes

* This system uses a **pretrained LLM via API** (no custom model training)
* Accuracy depends on prompt quality and retrieved context
* Designed for **first-pass code reviews**, not a replacement for human review

---

## 🚀 Future Improvements

* 🔗 GitHub PR integration
* 🧠 Vector database (FAISS / Pinecone) for smarter retrieval
* 📍 Inline code annotations
* 🌍 Multi-language support
* 📊 Review analytics dashboard

---

## ▶️ Setup Instructions

### Backend

```bash
cd backend
mvn spring-boot:run
```

### Frontend

```bash
cd frontend
npm install
npm start
```

---

## 🔑 Environment Variables

Create a `.env` file:

```
GROQ_API_KEY=your_api_key_here
```

---

## 🎯 Key Takeaway

This project demonstrates how to build a **real-world AI-powered system** by combining:

* Pretrained LLMs
* Retrieval-Augmented Generation (RAG)
* Full-stack engineering

---

## 📌 Author

Gowtham Roy
