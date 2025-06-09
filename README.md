# 💬 Java One-to-One Chat App (TCP Chat + UDP File Transfer)

A lightweight one-to-one chat application built using Java. Real-time messaging is handled over TCP sockets with a Swing-based GUI, and fast file transfer is supported using UDP for LAN/local usage.

---

## 🚀 Features

- 🧑‍🤝‍🧑 One-to-one private chat over TCP
- 🖥️ GUI built with Java Swing
- 🔄 Real-time message exchange
- 📁 File transfer over UDP (LAN/local)
- 🛠 Easy-to-understand and modular Java code
- 🔐 Option to add encryption & file verification (future scope)

---

## 🧰 Tech Stack

- **Language**: Java
- **Networking**:
  - TCP for message communication
  - UDP for file transfer
- **GUI**: Java Swing
- **Architecture**: Client-Server model

---

## 📦 Requirements

- Java JDK 8 or higher
- Operating System with Java support (Windows/Linux/Mac)
- Local or LAN network for full functionality

---

## 📂 Project Structure

chat-app/
├── src/
│ ├── Server.java # TCP chat server
│ ├── Client.java # TCP chat client with GUI
│ ├── ChatWindow.java # Swing-based GUI
│ ├── FileSenderUDP.java # UDP-based file sender
│ ├── FileReceiverUDP.java # UDP-based file receiver
│ └── utils/
│ └── Message.java # Optional message wrapper or utilities
├── README.md
└── LICENSE


---

## 🛠️ How to Run

### 🖥️ Compile the Project

▶️ Start the Server
```bash

java src/Server
```
▶️ Start the Client (on same/different machine)
```bash

java src/Client
```
##### ⚠️ Update the IP address in Client.java to point to the server’s IP for LAN use.

# 📁 UDP File Transfer (LAN)
The client can send a file using the Send File button or a command like:
```bash
/sendfile path/to/file.jpg
```
Files are sent using UDP datagrams.

The receiver saves the file to their local directory 

```bash 
(Downloads/ or chat_files/).
```
Supported: .txt, .png, .jpg, .pdf, etc.

## ⚠️ Notes:
UDP is fast but unreliable, so packet loss isn't handled by default.

You may add checksum verification or acknowledgements if needed.

# 🖼️ Screenshots


# 📌 Planned Improvements
✅ One-to-one chat over TCP

✅ File transfer using UDP

⏳ Add file integrity checks (e.g., SHA-256)

⏳ Transfer progress indicator

🔐 Basic end-to-end message encryption

💬 Add emoji or rich text support
