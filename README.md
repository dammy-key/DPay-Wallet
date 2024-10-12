
# D.PAY Wallet

D.PAY is a mobile wallet application that enables users to send and receive payments in the Nigerian currency (Naira). The app includes features such as utility bill payments, airtime recharge, and much more. This README provides information about the project setup, key features, installation, and usage.

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Technologies](#technologies)
- [Setup](#setup)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)
- [Usage](#usage)
- [License](#license)

## Project Overview

D.PAY is a Java-based backend integrated with PostgreSQL for secure data storage. The mobile app frontend is built using React Native, providing an intuitive and user-friendly interface for wallet operations. This application handles user sign-up, login, OTP verification, and utility payments.

### Key Functionalities:
- **Sign-Up**: Register with details like first name, last name, email, National Identification Number (NIN), and phone number. The account number is derived from the phone number.
- **Login**: Secure login with phone number and password. OTP is used for verification.
- **Utility Bill Payments**: Users can pay for services such as airtime recharge, DSTV, and GOTV.
- **Fund Transfer**: Users can send and receive money between D.PAY accounts.
  
## Features

1. **User Registration**: Users can register by providing their personal details, including phone number and email. An OTP is sent to verify their phone number.
2. **Login**: Secure login mechanism using phone number and password. OTP verification is required for successful login.
3. **Account Management**: The account number is extracted from the user’s phone number.
4. **Transaction Management**: View account balance, send and receive money.
5. **Utility Payments**: Pay for services such as airtime, data, and TV subscriptions (DSTV/GOTV).
6. **Notification System**: Users receive email notifications for transactions and updates.
  
## Technologies

### Backend:
- **Java (Spring Boot)** for core backend logic and APIs
- **PostgreSQL** for database management
- **Spring Security** for authentication and OTP generation
- **Spring Data JPA** for database interactions
- **Spring Boot Mail** for sending email notifications
- **Twilio API** for OTP verification (or any SMS service provider)

### Frontend:
- **React Native** for the mobile app interface
- **Redux** for state management
- **Axios** for API communication

### Other Tools:
- **Gradle** as the build tool
- **Postman** for testing API endpoints

## Setup

### Prerequisites

- Java 11+
- PostgreSQL
- Node.js and npm
- React Native development environment

### Backend Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/dpay-wallet.git
   ```
   
2. Navigate to the backend folder:
   ```bash
   cd dpay-wallet-backend
   ```

3. Update the `application.properties` with your database credentials:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/dpaywallet
   spring.datasource.username=dpayuser
   spring.datasource.password=Damilare143@
   spring.jpa.hibernate.ddl-auto=update
   spring.mail.host=smtp.gmail.com
   spring.mail.port=587
   spring.mail.username=your-email@gmail.com
   spring.mail.password=your-email-password
   ```

4. Run the application:
   ```bash
   ./gradlew bootRun
   ```

### Frontend Installation

1. Navigate to the frontend folder:
   ```bash
   cd dpay-wallet-frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Run the React Native app:
   ```bash
   npm start
   ```
   or, to run on an Android device:
   ```bash
   npm run android
   ```

## API Endpoints

### User Management

- **POST** `/api/auth/signup`: Registers a new user.
- **POST** `/api/auth/login`: Logs in a user and sends OTP for verification.
- **POST** `/api/auth/verify`: Verifies the OTP.

### Wallet Operations

- **GET** `/api/wallet/balance`: Gets the user’s account balance.
- **POST** `/api/wallet/transfer`: Transfers funds to another D.PAY account.
  
### Utility Payments

- **POST** `/api/payments/airtime`: Purchase airtime.
- **POST** `/api/payments/dstv`: Pay for DSTV subscription.
- **POST** `/api/payments/gotv`: Pay for GOTV subscription.

## Database Schema

### User Table:
| Field        | Type     | Description                  |
|--------------|----------|------------------------------|
| id           | UUID     | Primary key                  |
| first_name   | String   | User's first name             |
| last_name    | String   | User's last name              |
| email        | String   | User's email address          |
| phone_number | String   | User's phone number (used as account number) |
| password     | String   | User's hashed password        |
| nin          | String   | National Identification Number|
| otp          | String   | OTP for verification          |

### Wallet Table:
| Field        | Type     | Description                  |
|--------------|----------|------------------------------|
| id           | UUID     | Primary key                  |
| user_id      | UUID     | Foreign key referencing User  |
| balance      | Decimal  | Current account balance       |

### Transaction Table:
| Field        | Type     | Description                  |
|--------------|----------|------------------------------|
| id           | UUID     | Primary key                  |
| sender_id    | UUID     | User who initiated the transaction |
| receiver_id  | UUID     | User receiving the funds      |
| amount       | Decimal  | Transaction amount            |
| timestamp    | DateTime | Transaction date and time     |

## Usage

1. Register a new user by sending a POST request to `/api/auth/signup`.
2. Login with phone number and password via `/api/auth/login`.
3. Verify the OTP to access the wallet dashboard.
4. View balance, make payments, and perform transactions.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
