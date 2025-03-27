# 🚀 FastChat - 실시간 메시징 시스템 (Kafka & Redis 기반)

## 📌 프로젝트 소개
**FastChat**은 **Kafka와 Redis**를 활용하여 **고성능 실시간 메시징**을 지원하는 프로젝트입니다. 이 프로젝트는 **Spring Boot, JPA, Java**를 기반으로 구현되었으며, **객체지향 프로그래밍(OOP)** 원칙과 **테스트 주도 개발(TDD)**, **리팩토링**을 고려하여 설계되었습니다.

## 🌟 주요 목표
✅ **Spring 기반 기술 스택 심화 학습**  
✅ **Kafka와 Redis를 활용한 실시간 메시징 시스템 구현**  
✅ **MySQL 및 JPA 기반의 데이터 모델링 최적화**  
✅ **OOP, TDD, 리팩토링을 적용한 클린 아키텍처 구현**  
✅ **Docker 및 CI/CD를 활용한 자동화 배포 환경 구성**

## 🛠️ 기술 스택
- **Backend**: Java 17, Spring Boot 3.4.4, Spring Security
- **Database**: MySQL (RDBMS)
- **Caching**: Redis (List 기반 메시지 저장)
- **Message Queue**: Kafka (Producer & Consumer)
- **Testing**: JUnit 5, Mockito
- **DevOps**: Docker, Docker Compose, GitHub Actions (CI/CD)
- **Docs**: Swagger (SpringDoc OpenAPI)

## 📂 프로젝트 구조
```
📂 org.fastchat.fastchat
├── 📂 common
│   ├── 📂 config                 # Kafka, Redis, Security, Swagger 설정
│   ├── 📂 jwt                   # JWT 유틸 및 필터
│   └── 📂 exception             # 전역 예외 처리
│
├── 📂 chat
│   ├── 📂 controller            # ChatController
│   ├── 📂 service               # KafkaProducer, RedisCacheService 등
│   ├── 📂 dto                   # ChatMessageDTO
│   └── 📂 model                 # ChatMessage 등
│
├── 📂 user
│   ├── 📂 controller            # UserController
│   ├── 📂 service               # UserService
│   ├── 📂 repository            # UserRepository
│   ├── 📂 dto                   # UserRequestDTO, UserResponseDTO
│   └── 📂 model                 # User
│
└── FastChatApplication.java     # 메인 클래스
```

## 📌 API 명세서
### 🔹 사용자 API
| 메서드 | 엔드포인트 | 설명 |
|--------|-------------|------|
| POST   | `/api/users/register` | 회원가입 |
| POST   | `/api/users/login` | 로그인 (JWT 발급) |

### 🔹 채팅 API
| 메서드 | 엔드포인트 | 설명 |
|--------|-------------|------|
| POST   | `/api/chat/send?roomId={roomId}` | 메시지 전송 (Kafka + Redis 저장) |
| GET    | `/api/chat/history/{roomId}` | 최근 메시지 조회 (Redis 기반) |

## 🔐 인증 방식
- 로그인 시 JWT 토큰을 발급합니다.
- Swagger에서 JWT 인증을 통해 secured API 테스트가 가능합니다.

## ✅ 실행 방법
### 1️⃣ 프로젝트 클론
```bash
git clone https://github.com/사용자명/FastChat.git
cd FastChat
```

### 2️⃣ Docker로 의존 서비스 실행 (MySQL, Redis, Kafka)
```bash
docker-compose up -d
```

### 3️⃣ Spring Boot 애플리케이션 실행
```bash
./gradlew bootRun
```

### 4️⃣ API 테스트
- Postman 또는 Swagger 사용
- Swagger 주소: `http://localhost:8080/swagger-ui/index.html`

## 🧪 TDD 계획
- [x] UserService 테스트 (회원가입, 로그인)
- [x] KafkaProducer 테스트 (메시지 발행)
- [x] RedisCacheService 테스트 (메시지 저장/조회)
- [ ] KafkaConsumer 테스트 (자동 메시지 수신 처리)

---
💡 **이 프로젝트는 채팅 시스템 설계와 실시간 처리 기술에 대한 학습과 실습을 목적으로 제작되었습니다.**

