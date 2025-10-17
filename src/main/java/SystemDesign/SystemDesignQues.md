#### ✅ Common System Design Questions:

* Design Cab Booking System (Uber/Ola) - Ride_Sharing_cdesign.md in detail

* Design Instagram / Twitter / Facebook/ LinkedIn (Social Media)
* Design BookMyShow / Ticket-Booking System
* Design Food Delivery App (Zomato/Swiggy)
* Design WhatsApp / Chat App
* Design Rate Limiter (Token Bucket, Leaky Bucket)
* Design Google Docs (Real-time Collaboration)
* Design Amazon / Flipkart (E-commerce)
* Design Notification System (Push/SMS/Email)
* Design URL Shortener (Bitly)
* Design Payment Gateway
* Design Parking Lot System
* Design Stock Trading Platform (like Zerodha)
* Design Distributed Cache (like Redis)
* Design YouTube clone
* Design Google Search Type a
* Design Google Calender
* Design Hotel Booking System


---

## 1. Design Social Media - Instagram/ Twitter/ Facebook

### Functional Requirements
* Post content (image, video, text), follow users, like, comment
* Feed generation
* Notifications, DMs
* User Profile

### Flow Diagram
UI → Application Layer (FeedService / PostService / NotificationService) ↔ Cache ↔ DB

### Class Structure
| Entity       | Attributes                                          |
| ------------ | --------------------------------------------------- |
| User         | userId, name, email, password, followers, following |
| Post         | postId, contentUrl, caption, timestamp, userId      |
| Comment      | commentId, text, userId, postId, timestamp          |
| Like         | likeId, userId, postId                              |
| FeedService  | getFeed(userId), updateFeed(userId, post)           |

---

## 2. Design BookMyShow / Ticket Booking

### Functional Requirements
* Movie listing, seat selection, booking
* Payment, cancellations

### Flow Diagram
UI → Application Layer (BookingService/ MovieService/ PaymentService) ↔ DB

### Class Structure
| Entity  | Attributes                                     |
| ------- | ---------------------------------------------- |
| User    | userId, name, email, phone                     |
| Movie   | movieId, name, language, duration, genre       |
| Theatre | theatreId, name, location                      |
| Show    | showId, movieId, theatreId, timing, seats\[]   |
| Booking | bookingId, userId, showId, seatNumbers, status |
| Payment | paymentId, bookingId, status, mode             |

---

## 3. Design Food Delivery App (Zomato/Swiggy)

### Functional Requirements
* Search restaurants, place orders, real-time tracking

### Flow Diagram
UI → Application Layer (OrderService/ RestaurantService/ DeliveryService) ↔ DB

### Class Structure
| Entity     | Attributes                                     |
| ---------- |------------------------------------------------|
| User       | userId, name, email, location                  |
| Restaurant | restaurantId, name, menu[], location           |
| MenuItem   | itemId, name, price, description               |
| Order      | orderId, userId, restaurantId, items[], status |
| Delivery   | deliveryId, orderId, deliveryBoyId, status     |

---

## 4. Design WhatsApp / Chat App

### Functional Requirements
* Real-time messaging, group chat, media sharing

### Flow Diagram
Client → WebSocket → MessageService ↔ DB

### Class Structure
| Entity     | Attributes                                        |
|------------| ------------------------------------------------- |
| User       | userId, name, phone                               |
| Message    | messageId, senderId, receiverId, text, timestamp  |
| Group      | groupId, name, members\[]                         |
| Group_Chat | chatId, messages\[], participants\[], lastMessage |

Remove chats from database whose values are older than 24 hrs.

---

## 5. Design Rate Limiter (Token Bucket / Leaky Bucket)

### Functional Requirements
* Limit requests per user/IP/timeframe

### Flow Diagram
Client → API Gateway → RateLimiter → Service

### Class Structure
| Entity      | Attributes                             |
| ----------- | -------------------------------------- |
| User/IP     | userId / IP                            |
| Bucket      | tokens, lastRefillTimestamp, rateLimit |
| RateLimiter | allowRequest(user/IP)                  |

---

## 6. Design Google Docs (Real-time Collaboration)

### Functional Requirements
* Real-time editing, sharing, versioning

### Flow Diagram
UI → WebSocket → CollaborationService ↔ DB

### Class Structure Table
| Entity    | Attributes                                    |
| --------- | --------------------------------------------- |
| User      | userId, name, email                           |
| Document  | docId, title, content, ownerId, sharedWith\[] |
| Operation | type, position, char, userId, timestamp       |

---

## 7. Design Amazon / Flipkart (E-commerce)

### Functional Requirements
* Browse items, cart, orders, reviews, payments

### Flow Diagram
UI → ProductService / CartService / OrderService / Payment ↔ DB

### Class Structure Table
| Entity  | Attributes                              |
| ------- | --------------------------------------- |
| User    | userId, name, address, paymentInfo      |
| Product | productId, name, price, stock, reviews[] |
| Cart    | cartId, userId, products[]              |
| Order   | orderId, userId, productList[], status  |
| Payment | paymentId, orderId, amount, status      |

---

## 8. Design Notification System (Push/SMS/Email)

### Functional Requirements
* Send notifications via multiple channels

### Flow Diagram
Event Trigger → NotificationService (processing - users, emailAddress of those users, linkage, email template) → ChannelService (sending through diff channels) → Queue

### Class Structure
| Entity         | Attributes                               |
| -------------- | ---------------------------------------- |
| User           | userId, name, email, phone               |
| Notification   | id, type, message, receiverId, timestamp |
| ChannelService | sendSMS(), sendEmail(), sendPush()       |

---

## 9. Design URL Shortener (Bitly)

### Functional Requirements
* Generate a shorter URL, Redirect short URL to original URL
* Set link expiry time, Track click counts
* User registration & login, Fetch all URLs created by user

### Flow Diagram
UI → Submit Original URL → Application Layer (URLService) → Check if already shortened → If not, then Generate short URL (using hashing / base62 encoding) → Save to Database ←→ Use Cache (for fast redirection) → Return shortened URL to UI

### Class Structure

#### Entities
| Entity      | Attributes                                                                                   |
|-------------|----------------------------------------------------------------------------------------------|
| User        | userId, name, email, password, createDate                                                    |
| URL         | urlId, userId, originalUrl, shortUrl, clickCount, createDate, expiryDate |

#### Services
| Entity      | Attributes                                                                                       |
|-------------|--------------------------------------------------------------------------------------------------|
| URLService  | createShortUrl(originalUrl, userId, expiryDate), getOriginalUrl(shortUrl), incrementClickCount() |
| AuthService | registerUser(name, email, password), loginUser(email, password)                                  |

### 💡 Short URL Generation Techniques
- Hash original URL (e.g., MD5/SHA256) + take first few characters
- Use Base62 encoding of auto-increment ID
- Use random 6-8 char alphanumeric string with collision checking

---

## 10. Design Payment Gateway

### Functional Requirements
* Validate card, process payments, retry mechanism

### Flow Diagram
Client → PaymentService → BankAPI / 3rd Party ↔ DB

### Class Structure
| Entity      | Attributes                           |
| ----------- | ------------------------------------ |
| User        | userId, name, cardInfo               |
| Payment     | paymentId, amount, status, timestamp |
| BankGateway | processPayment(), verifyStatus()     |

---

## 11. Design Parking Lot System

### Functional Requirements
* Multiple floors, spot types, real-time availability

### Flow Diagram
UI → ParkingService ↔ SensorData ↔ DB

### Class Structure
| Entity  | Attributes                                   |
| ------- | -------------------------------------------- |
| Vehicle | vehicleId, number, type                      |
| Spot    | spotId, type, isAvailable, floor             |
| Floor   | floorId, spots\[]                            |
| Ticket  | ticketId, vehicleId, spotId, inTime, outTime |

---

## 12. Design Stock Trading Platform (like Zerodha)

### Functional Requirements
* Place buy/sell orders, view portfolio, real-time prices

### Flow Diagram
UI → OrderService/ MarketDataService ↔ DB ↔ External APIs

### Class Structure
| Entity | Attributes                                     |
| ------ | ---------------------------------------------- |
| User   | userId, name, portfolio\[], balance            |
| Order  | orderId, userId, type, quantity, price, status |
| Stock  | stockId, name, currentPrice                    |

---

## 13. Design Distributed Cache (like Redis)

### Functional Requirements
* Fast key-value store, eviction policies, persistence

### Flow Diagram
Client → CacheLayer ↔ DB

### Class Structure Table
| Entity       | Attributes                                     |
| ------------ | ---------------------------------------------- |
| CacheNode    | nodeId, keys\[], lastAccessed                  |
| CacheManager | evictionPolicy, get(), set(), remove(), sync() |

---

## 14. YouTube Clone - System Design

### Functional Requirements
- Video: Upload, View, Search, Like/ Dislike, Comment
- Subscribe to Channel
- View History
- Get Recommended Videos
- User Authentication
- Save to Playlist

### Flow / UML Diagram (High-level)
UI (Hit the API) → Application Layer (VideoService/ UserService/ SearchService → Database ←→ Cache (Redis, etc.) → Response back to UI with data (video/playlists/search results)

### Class Structure

#### Entities:
| Entity          | Attributes                                                                      |
|-----------------|---------------------------------------------------------------------------------|
| User            | userId, name, email, password, channelId, createdDate, isPremium                |
| Channel         | channelId, userId, name, description, subscriberCount, videos                   |
| Video           | videoId, title, description, url, uploadDate, views, likes, dislikes, channelId |
| Comment         | commentId, videoId, userId, text, timestamp                                     |
| Like            | userId, videoId, isLiked                                                        |
| Subscription    | subscriberId, channelId, subscribedDate                                         |
| Playlist        | playlistId, userId, name, List\[Video\], createdDate                            |
| History         | userId, List<VideoId, timestamp>                                                |
| Recommendation  | userId, List<VideoId>                                                           |

#### Services
| Entity         | Attributes                                                                                   |
|----------------|----------------------------------------------------------------------------------------------|
| UserService    | registerUser(), loginUser(), subscribeChannel(), getUserHistory(), getRecommendations()      |
| VideoService   | uploadVideo(), deleteVideo(), getVideoById(), updateViews()                                  |
| SearchService  | searchByTitle(), searchByTag(), searchByChannel()                                            |
---

## 14. Design LinkedIn

### Functional Requirements

* User profiles, professional network (connect), posts, job listings, messaging

### Flow Diagram

UI → ProfileService / FeedService / MessagingService / JobService ↔ DB

### Class Structure Table

| Entity     | Attributes                                                 |
| ---------- | ---------------------------------------------------------- |
| User       | userId, name, email, phone, headline, experience, skills   |
| Connection | connectionId, requesterId, receiverId, status              |
| Post       | postId, content, timestamp, userId                         |
| Message    | messageId, senderId, receiverId, text, timestamp           |
| Job        | jobId, companyId, title, description, location, postedDate |
| Company    | companyId, name, description, industry                     |

---

## 15. Design Google Search Typeahead

### Functional Requirements
* Provide real-time search suggestions as user types
* Suggestions ranked by frequency/popularity
* Support for prefix matching

### Flow Diagram
Client UI → TypeaheadService → Trie / PrefixIndex ↔ DB / Cache

### Class Structure Table
| Entity           | Attributes                                                     |
| ---------------- | -------------------------------------------------------------- |
| TrieNode         | children: Map\<Character, TrieNode>, isEnd: boolean, freq: int |
| TypeaheadService | insert(word), getSuggestions(prefix), updateFrequency(word)    |
| Suggestion       | word, frequency                                                |

---

## 16. Design Google Calendar

### Functional Requirements
* Create/view/edit events, set reminders, invite participants, recurring events

### Flow Diagram
Client UI → Application Layer (CalendarService, NotificationService) ↔ DB ↔ Message Queue

### Class Structure
| Entity                 | Attributes                                                                |
|------------------------|---------------------------------------------------------------------------|
| User                   | userId, name, email                                                       |
| Event                  | eventId, title, description, startTime, endTime, creator userId           |
| Attendee               | eventId, userId, status           |
| Reminder/ Notification | reminderId, eventId, timeBeforeEvent      |
| EventService           | createEvent(), deleteEvent(), updateEvent(), getEvents(userId, dateRange) |
| NotificationService    | scheduleNotification(event), sendNotification()                           |
| RecurrenceRule         | type: \[DAILY, WEEKLY, MONTHLY\], interval, endDate                       |

---

## 17. Design Hotel Booking System

### Functional Requirements
* Search hotels, check availability, book rooms, payment, cancellations, review

### Flow Diagram
Client UI → Application Layer (SearchService, BookingService, AvailabilityService, PaymentService, NotificationService) ↔ DB / Cache

### Class Structure
| Entity             | Attributes                                                                  |
| ------------------ | --------------------------------------------------------------------------- |
| User               | userId, name, email, phone                                                  |
| Hotel              | hotelId, name, address, amenities, rating                                   |
| Room               | roomId, hotelId, roomType, price, isAvailable                               |
| Booking            | bookingId, userId, roomId, checkIn, checkOut, status                        |
| Payment            | paymentId, bookingId, status, amount, method                                |
| Review             | reviewId, hotelId, userId, rating, comment                                  |
| SearchService      | searchHotels(location, filters)                                             |
| BookingService     | createBooking(), cancelBooking(), getBookingDetails()                       |
| AvailabilityService| getAvailableRooms(hotelId, checkIn, checkOut)                              |
| PaymentService     | initiatePayment(bookingId), confirmPayment(paymentId)                       |





