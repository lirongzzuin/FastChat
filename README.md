# 🚀 FastChat - 실시간 메시지 큐 시스템

## 📌 프로젝트 소개
**FastChat**은 Redis와 Kafka를 활용한 실시간 채팅 및 알림 시스템입니다. 
Kafka를 사용한 메시지 큐 기반 비동기 처리를 통해 메시지를 빠르게 전송하며, Redis를 활용하여 최신 메시지 및 사용자 상태를 캐싱합니다. 

## 🎯 주요 기능
✅ **실시간 채팅** - WebSocket을 활용한 빠른 메시지 전송  
✅ **Kafka 기반 비동기 메시징** - 메시지 송수신을 Kafka Queue로 처리  
✅ **Redis 캐싱** - 최근 50개의 메시지를 Redis에 저장하여 빠르게 조회  
✅ **Redis Pub/Sub** - 실시간 메시지 스트리밍 지원  
✅ **JWT 인증** - 사용자 로그인 및 보안 강화  
✅ **Docker 지원** - 손쉬운 배포를 위한 컨테이너 환경 제공  

## 🛠️ 기술 스택
- **Backend**: Spring Boot, Spring Security, WebSocket
- **Database**: MySQL
- **Caching**: Redis (Sorted Set 활용)
- **Message Queue**: Kafka (Producer & Consumer)
- **Auth**: JWT (Json Web Token)
- **DevOps**: Docker, Docker Compose

## 📂 프로젝트 구조
```
📂 fastchat-backend
 ├── 📂 config
 │   ├── KafkaConfig.java  # Kafka 설정
 │   ├── RedisConfig.java  # Redis 설정
 │   ├── SecurityConfig.java  # JWT 인증 설정
 ├── 📂 controller
 │   ├── ChatController.java  # 채팅 관련 API
 │   ├── UserController.java  # 사용자 관련 API
 ├── 📂 service
 │   ├── ChatService.java  # 채팅 처리 로직
 │   ├── UserService.java  # 사용자 관리 로직
 ├── 📂 repository
 │   ├── ChatRepository.java  # 메시지 저장소 (JPA)
 │   ├── UserRepository.java  # 사용자 저장소 (JPA)
 ├── 📂 domain
 │   ├── User.java  # 사용자 엔티티
 │   ├── ChatMessage.java  # 메시지 엔티티
 ├── 📂 dto
 │   ├── ChatMessageDTO.java  # 메시지 데이터 전송 객체
 │   ├── UserDTO.java  # 사용자 데이터 전송 객체
 ├── FastChatApplication.java  # 메인 애플리케이션
```

## 📌 API 명세서
### 🔹 **사용자 관련 API**
| HTTP Method | Endpoint | 설명 |
|------------|---------|------|
| **POST** | `/api/users/register` | 사용자 회원가입 |
| **POST** | `/api/users/login` | 사용자 로그인 (JWT 발급) |
| **GET** | `/api/users/me` | 로그인한 사용자 정보 조회 |

### 🔹 **채팅 관련 API**
| HTTP Method | Endpoint | 설명 |
|------------|---------|------|
| **POST** | `/api/chat/send` | 메시지 전송 (Kafka Producer) |
| **GET** | `/api/chat/history/{roomId}` | 특정 채팅방의 메시지 히스토리 조회 (Redis) |
| **GET** | `/api/chat/subscribe/{roomId}` | 실시간 채팅 구독 (Redis Pub/Sub) |

### 🔹 **Kafka 메시징 관련 API**
| HTTP Method | Endpoint | 설명 |
|------------|---------|------|
| **POST** | `/api/kafka/send` | Kafka 메시지 발행 |
| **GET** | `/api/kafka/consume` | Kafka 메시지 소비 |

## 🔥 실행 방법
### 1️⃣ **프로젝트 클론**
```sh
git clone https://github.com/YOUR_GITHUB_ID/FastChat.git
cd FastChat
```

### 2️⃣ **Docker 실행 (Kafka & Redis 포함)**
```sh
docker-compose up -d
```

### 3️⃣ **Spring Boot 애플리케이션 실행**
```sh
./gradlew bootRun  # 또는
mvn spring-boot:run
```

### 4️⃣ **API 테스트** (Postman / Swagger 활용)
- `http://localhost:8080/api/users/register` - 회원가입
- `http://localhost:8080/api/users/login` - 로그인 (JWT 발급)
- `http://localhost:8080/api/chat/send` - 메시지 전송 (WebSocket 활용)


