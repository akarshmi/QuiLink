<div align="center">

# 🔗 QuiLink

### *Because your URLs are way too long and you know it.*

**A URL shortener that's actually engineered well.**  
*No, we don't just `Math.random()` our way through life like some kind of caveman.*

[![Java](https://img.shields.io/badge/Java-25-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![MongoDB](https://img.shields.io/badge/MongoDB-Atlas-47A248?style=for-the-badge&logo=mongodb&logoColor=white)](https://www.mongodb.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)](https://opensource.org/licenses/MIT)

---

*"I have a URL the size of a CVS receipt"*

**→ QuiLink it.**

</div>

---

## 🎯 Wait, Another URL Shortener?

Yeah, yeah. There's bitly, tinyurl, and approximately 47,000 weekend projects on GitHub that do the same thing. So why does **this** one exist?

Because most of them generate short codes like this:

```java
String shortCode = UUID.randomUUID().toString().substring(0, 6); // 😬
```

And then **pray** to the database gods that there's no collision. And when there *is* a collision? They just… try again. And again. And again. Like some kind of while-loop masochist.

**We don't do that here.**

QuiLink uses a **Snowflake ID generator + Base62 encoding** pipeline that guarantees uniqueness *by design*, not by hope and prayer. No collision checks. No retry loops. No existential crises at 3 AM.

---

## ✨ What Can It Do?

| Feature | Description | Sarcastic Commentary |
|---------|-------------|---------------------|
| 🔗 **Shorten URLs** | Turn monstrous URLs into cute little links | *Your 247-character URL has entered the chat. QuiLink has left the room.* |
| 🔄 **Redirect** | GET the short link → land on the original | *It's like a travel agent for your browser.* |
| 🆔 **Snowflake IDs** | Custom ID generator that guarantees uniqueness | *Like snowflakes, but useful.* |
| 🔡 **Base62 Encoding** | Compact, URL-safe short codes | *Letters AND numbers? Revolutionary. We know.* |
| 📊 **Click Tracking** | Count how many people actually click your link | *Spoiler: It's fewer than you think.* |
| ⏰ **Expiration Validation** | Links can expire. Like milk. | *Your link has a shelf life. Plan accordingly.* |
| 🚫 **Active/Inactive Toggle** | Disable links without deleting them | *Soft delete energy. We're all about that non-destructive lifestyle.* |
| 🛡️ **Global Exception Handling** | Consistent, clean error responses | *Our errors look better than most APIs' success responses.* |
| 📈 **Actuator Monitoring** | Health checks, metrics, all the ops goodies | *Because you can't fix what you can't see.* |

---

## 🧠 The Secret Sauce: How Short Codes Are Born

Most URL shorteners: *generates random string* → *checks DB* → *collision?!* → *panic* → *regenerate* → *pray* → *checks DB again* → *sigh of relief*

**QuiLink:**

```
Long URL
    │
    ▼
┌──────────────────────┐
│  Snowflake ID Gen    │  ← Deterministic. Unique. No drama.
└──────────┬───────────┘
           │
           ▼
    64-bit Unique ID
    1953428719238471680
           │
           ▼
┌──────────────────────┐
│  Base62 Encoding     │  ← URL-safe. Compact. Chef's kiss.
└──────────┬───────────┘
           │
           ▼
    Short Code
    Ovh3RBx0ka
```

### Why Snowflake? ❄️

| What You Want | What Random Gives You | What Snowflake Gives You |
|---------------|----------------------|-------------------------|
| Uniqueness | *fingers crossed* | **Guaranteed** |
| Ordering | *what ordering?* | **Chronological** |
| Collision checks | *every. single. time.* | **None needed** |
| Retry loops | *while(true) { suffer }* | **No retries** |
| Multi-instance scaling | *good luck with that* | **Built-in** |

Sure, the short codes are a tiny bit longer than `xK9mP2`. But you know what's longer? The debugging session at 2 AM when your random generator finally collides in production.

**We choose sleep.**

---

## 🏗️ Tech Stack

### The Backend Heavyweights

```
┌─────────────────────────────────────┐
│           ☕ Java 25 (LTS)          │  ← Yes, 25. We live in the future.
├─────────────────────────────────────┤
│        🌱 Spring Boot 4             │  ← The framework that pays bills.
├─────────────────────────────────────┤
│        🌿 Spring MVC                │  ← RESTful and we mean it.
├─────────────────────────────────────┤
│    🍃 Spring Data MongoDB           │  ← Documents, not tables. Deal with it.
├─────────────────────────────────────┤
│          📦 Maven                    │  ← XML and chill.
├─────────────────────────────────────┤
│         🪶 Lombok                   │  ← Because getters/setters are a crime.
├─────────────────────────────────────┤
│        🗺️ MapStruct                │  ← DTO mapping on autopilot.
└─────────────────────────────────────┘
```

### The Database

🍃 **MongoDB Atlas** — Because not everything needs to be relational. Some things in life are just… documents. Deep, unstructured documents. Like your therapy journal.

### The Monitoring

🩺 **Spring Boot Actuator** — So you can watch your application like a concerned parent. `/actuator/health` is basically "I'm fine" but for microservices.

---

## 📁 Project Structure

```
src/main/java/dev/akarshmi/quilink
│
├── 📂 common
│   ├── 📂 constants          ← Things that never change. Like your love for coffee.
│   └── 📂 mapper             ← MapStruct sits here. Mapping DTOs like a boss.
│
├── 📂 config                  ← Spring configs. The wiring behind the walls.
│
├── 📂 exception               ← Where errors go to get dressed up pretty.
│
├── 📂 redirect                ← The "take me there" module
│   ├── 📂 controller          ← "Hey, where do I go?"
│   ├── 📂 dto                 ← Data dressed for the occasion
│   ├── 📂 service             ← The brain
│   └── 📂 serviceImpl         ← The brain's actual implementation
│
├── 📂 security
│   └── 📂 auth                ← "Who goes there?" (coming soon™)
│
└── 📂 shortener               ← The "make it small" module
    ├── 📂 controller          ← API endpoints live here
    ├── 📂 document            ← MongoDB documents. Not PDFs.
    ├── 📂 dto                 ← Request/Response objects
    ├── 📂 repository          ← Spring Data magic
    ├── 📂 service             ← Business logic interface
    ├── 📂 serviceImpl         ← Where the actual work happens
    └── 📂 util                ← Snowflake gen, Base62, etc.
```

> **Notice something?** We organize by **business module**, not by dumping every controller, service, and repository into one giant package like some kind of animal. Related code stays together. Novel concept, right?

---

## 🔌 REST API

### Create a Short URL

```http
POST /api/v1/shortener
Content-Type: application/json

{
  "url": "https://example.com/this/is/a/ridiculously/long/url/that/nobody/wants/to/see"
}
```

**Response:**

```json
{
  "shortCode": "Ovh3RBx0ka",
  "shortUrl": "http://localhost:8080/r/Ovh3RBx0ka"
}
```

*Congratulations. You just saved 236 characters. The planet thanks you.*

---

### Redirect to Original URL

```http
GET /r/{shortCode}
```

**Response:** `302 Found` with `Location` header pointing to the original URL.

*It's like magic, except it's just a database lookup and an HTTP redirect. But magic sounds cooler.*

---

### Get Link Statistics

```http
GET /api/v1/shortener/{shortCode}/stats
```

**Response:**

```json
{
  "originalUrl": "https://example.com",
  "createdAt": "2026-07-20T09:00:00Z",
  "expiresAt": "2027-07-20T09:00:00Z",
  "active": true,
  "clickCount": 42
}
```

*42 clicks? That's the answer to life, the universe, and everything. Coincidence? We think not.*

---

### Update Destination URL

```http
PUT /api/v1/shortener/{shortCode}
Content-Type: application/json

{
  "url": "https://new-destination.com"
}
```

*Same short code, new destination. Like changing your flight but keeping the ticket.*

---

### Delete a Short URL

```http
DELETE /api/v1/shortener/{shortCode}
```

*Gone. Reduced to atoms. (Well, deleted from MongoDB. Which is basically the same thing.)*

---

## 🚨 Error Handling

We don't just throw stack traces at you like some kind of barbarian. Every error comes back looking like this:

```json
{
  "timestamp": "2026-07-20T09:52:50Z",
  "status": 404,
  "error": "Not Found",
  "message": "Short code not found",
  "path": "/r/abc123"
}
```

*Consistent. Clean. Almost makes you want to trigger errors just to see how pretty they look.*

### Common Error Scenarios

| Status | When | Why It's Your Fault |
|--------|------|-------------------|
| `404` | Short code doesn't exist | You made that up. |
| `410` | Link has expired | Time waits for no URL. |
| `403` | Link is inactive | Someone turned it off. Maybe you. |
| `400` | Invalid request body | Read the docs. Please. |

---

## 🔄 The Redirect Flow (Visualized, Because We Care)

```
   👤 Client
     │
     │  GET /r/Ovh3RBx0ka
     ▼
┌─────────────┐
│  Controller  │  "I'll handle this."
└──────┬──────┘
       │
       ▼
┌─────────────┐
│   Service    │  "Let me check the database…"
└──────┬──────┘
       │
       ▼
┌─────────────┐
│   MongoDB    │  "Yeah, I got that URL."
└──────┬──────┘
       │
       ▼
┌─────────────────────────────────┐
│         Validation Check        │
│  ✓ Exists?   ✓ Active?   ✓ Not Expired?  │
└──────┬──────────────────────────┘
       │ All checks pass ✅
       ▼
┌─────────────┐
│  Click ++    │  "Another satisfied customer."
└──────┬──────┘
       │
       ▼
   302 Redirect → 🌐 Original Website
```

*If any check fails, the whole thing gracefully falls apart with a proper error response. No drama. Just professionalism.*

---

## 🚀 Running the Project

### 1. Clone It (Obviously)

```bash
git clone https://github.com/akarshmi/QuiLink.git
cd QuiLink
```

*The hardest part is behind you. Just kidding.*

### 2. Configure Environment Variables

Create a `.env` file or set these in your environment:

```env
DATABASE_URL=<your-mongodb-connection-string>
```

*Yes, you need a MongoDB connection string. No, we can't magic one into existence. Go to [MongoDB Atlas](https://www.mongodb.com/atlas), create a free cluster, and grab the URI. It takes like 3 minutes. You've spent longer reading this README.*

### 3. Build

```bash
mvn clean install
```

*Watch Maven download the internet. It's a bonding experience.*

### 4. Run

```bash
mvn spring-boot:run
```

Head over to **http://localhost:8080** and start shortening URLs like a responsible adult.

---

## 🩺 Monitoring

Spring Boot Actuator is enabled because flying blind is for pilots in movies, not engineers in production.

| Endpoint | What It Does |
|----------|-------------|
| `/actuator` | "Here's everything I can tell you" |
| `/actuator/health` | "Am I alive? Yes." |
| `/actuator/metrics` | "Here are numbers. Do with them what you will." |

*It's like a fitness tracker for your app, except your app actually listens to it.*

---

## 🏛️ Design Decisions (Or: Why We Did What We Did)

### Modular Architecture

Instead of dumping everything into packages like `controllers/`, `services/`, `repositories/` (the "throw it all in a drawer" approach), we organize by **business module**:

```
✅ shortener/  → Everything about shortening
✅ redirect/   → Everything about redirecting
✅ security/   → Everything about auth
✅ common/     → Everything shared

❌ NOT:
controllers/  → Every controller ever
services/     → Every service ever
repositories/ → Every repository ever
```

*It's almost like code should be organized by what it DOES, not by what it IS. Wild concept.*

### DTO-Based Communication

Controllers never expose MongoDB documents directly. We use DTOs because:

- 🔒 **Security** — Don't accidentally leak internal fields
- 🔄 **Flexibility** — Change the DB schema without breaking the API
- 🧹 **Clarity** — The API contract is explicit, not "whatever MongoDB happens to store"

*If your API returns your database document directly, we need to have a conversation.*

### Snowflake + Base62

We could've used UUIDs (too long), random strings (collision risk), or auto-incrementing IDs (doesn't scale). Instead:

- **Snowflake** → Unique, ordered, scalable IDs
- **Base62** → Compact, URL-safe representation

*It's the "why not both?" of identifier generation.*

---

## 🔮 Future Improvements

Things we want to build when we're not busy refactoring:

- [ ] 🧠 **Redis Caching** — Because hitting MongoDB for every redirect is so 2023
- [ ] 🔐 **JWT Authentication** — So not just anyone can shorten URLs
- [ ] 👤 **User Accounts** — "My links" pages and all that
- [ ] ✏️ **Custom Aliases** — Want `quili.ink/yourname`? We got you
- [ ] 📱 **QR Code Generation** — For the offline world (yes, it still exists)
- [ ] 📊 **Detailed Analytics** — Geographic data, devices, browsers, the works
- [ ] 🚦 **Rate Limiting** — Because someone *will* try to DDOS your shortener
- [ ] 🐳 **Docker Support** — Containerize all the things
- [ ] 🔄 **CI/CD Pipeline** — Automate deployments like a civilized person
- [ ] 🌐 **Custom Domains** — Your brand, your short links
- [ ] ⚡ **Async Click Analytics** — Because counting clicks shouldn't slow down redirects

---

## 📚 What You'll Learn (If You Pay Attention)

This project is basically a crash course in backend engineering that doesn't put you to sleep:

| Concept | What You'll Actually Learn |
|---------|---------------------------|
| REST API Design | How to build APIs that don't make developers cry |
| Spring Boot Architecture | Why Spring is the framework that keeps on giving |
| MongoDB Document Modeling | Documents ≠ tables, and that's okay |
| Unique ID Generation | Snowflake IDs and why they're brilliant |
| URL Encoding Strategies | Base62 and beyond |
| Exception Handling | Errors happen. Handle them gracefully. |
| Clean Project Organization | Code structure that scales |
| Backend Scalability | Thinking beyond "it works on my machine" |

---

## 🤝 Contributing

1. Fork the repo
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

*Please don't push directly to main. We're civilized people.*

---

## 📄 License

This project is available under the **MIT License**.

*Translation: Do whatever you want with it, just don't sue us.*

---

<div align="center">

### Made with ☕ and existential dread

**[⬆ Back to Top](#-quilink)**

*If you read this entire README, you deserve a cookie. 🍪*

*Unfortunately, we're a URL shortener, not a bakery.*

---

⭐ **Star this repo if it made you smile (or if you actually learned something).** ⭐

</div>
