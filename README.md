# 🚀 FastChat - 실시간 메시지 큐 시스템 (Kafka & Redis 기반)

## 📌 프로젝트 소개
FastChat은 **Kafka와 Redis**를 활용하여 **고성능 실시간 메시징**을 지원하는 프로젝트입니다. 
이 프로젝트는 **Spring Boot, JPA, Kotlin**, 그리고 **도메인 주도 설계(DDD)**를 기반으로 설계되었으며, 
**객체지향 설계(OOP), 테스트 주도 개발(TDD), 리팩토링**을 고려하여 구현됩니다.

## 🎯 주요 목표
✅ **Java/Kotlin 기반의 Spring 기술 스택 숙련도 향상**  
✅ **Kafka와 Redis를 활용한 고성능 메시징 시스템 구현**  
✅ **RDBMS(MySQL) 및 JPA를 활용한 데이터 모델링 최적화**  
✅ **OOP, TDD, Refactoring을 적용한 클린 코드 작성**  
✅ **Docker 및 CI/CD를 활용한 배포 자동화**  

## 🛠️ 기술 스택
- **Backend**: Java 17 / Kotlin, Spring Boot 3.x, Spring Security
- **Database**: MySQL (RDBMS 모델링 최적화)
- **Caching**: Redis (Sorted Set 활용)
- **Message Queue**: Kafka (Producer & Consumer)
- **Testing**: JUnit 5, Mockito, Testcontainers (TDD 적용)
- **Architecture**: DDD, Hexagonal Architecture 적용
- **DevOps**: Docker, Docker Compose, CI/CD (GitHub Actions)

## 📂 프로젝트 구조 (DDD 기반 설계)
```
📂 fastchat-backend
 ├── 📂 config
 │   ├── KafkaConfig.kt  # Kafka 설정
 │   ├── RedisConfig.kt  # Redis 설정
 │   ├── SecurityConfig.kt  # JWT 인증 설정
 ├── 📂 domain  # 도메인 계층
 │   ├── 📂 model  # 핵심 엔티티
 │   │   ├── User.kt  # 사용자 모델
 │   │   ├── ChatMessage.kt  # 채팅 메시지 모델
 │   ├── 📂 repository  # 도메인 저장소
 │   │   ├── ChatRepository.kt  # 메시지 저장소 (JPA)
 │   │   ├── UserRepository.kt  # 사용자 저장소 (JPA)
 ├── 📂 application  # 서비스 계층
 │   ├── ChatService.kt  # 채팅 로직
 │   ├── UserService.kt  # 사용자 관리 로직
 ├── 📂 infrastructure  # 인프라 계층
 │   ├── KafkaProducer.kt  # Kafka Producer
 │   ├── KafkaConsumer.kt  # Kafka Consumer
 │   ├── RedisCacheService.kt  # Redis 캐싱 로직
 ├── 📂 presentation  # 컨트롤러 계층
 │   ├── ChatController.kt  # 채팅 API
 │   ├── UserController.kt  # 사용자 API
 ├── FastChatApplication.kt  # 메인 애플리케이션
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
git clone https://github.com/lirongzzuin/FastChat.git
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

## 📜 TDD 적용 계획
✅ **UserService 테스트** (회원가입, 로그인)  
✅ **ChatService 테스트** (메시지 저장, Kafka 발행)  
✅ **Redis 캐싱 테스트** (최근 메시지 캐싱 확인)  
✅ **Kafka Consumer 테스트** (메시지 정상 처리 확인) 

