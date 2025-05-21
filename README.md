# 🧩 Đặt lịch hẹn trực tuyến - Microservices Architecture

## 📘 Giới thiệu hệ thống

Đây là một hệ thống **Đặt lịch hẹn trực tuyến** được xây dựng theo kiến trúc **Microservices**. Các dịch vụ trong hệ thống giao tiếp với nhau thông qua **REST API** và được điều phối bởi một **API Gateway**.

## 🧾 Use Case: Đặt lịch hẹn trực tuyến
### 🎯 Mục tiêu:
Người dùng có thể đặt trước lịch hẹn cho một dịch vụ nào đó.Sau khi người dùng chọn một loại dịch vụ và khung thời gian đi kèm thì hệ thống sẽ kiểm tra các nhân viên / chuyên viên có sẵn để cung cấp dịch vụ, lưu thông tin lịch hẹn, gửi email xác nhận.

### 🔁 Luồng hoạt động:
1. Sau khi đăng nhập, người dùng chọn **một dịch vụ** và **thời gian**.
2. Frontend gọi API `/scheduling` -> Gateway -> Scheduling Servive để lấy danh sách nhân viên / chuyên viên có sẵn
3. Người dùng có thể chọn một nhân viên và ấn đặt lịch, nếu không chọn thì hệ thống sẽ tự chọn
3. Frontend gọi API `/appointment` → Gateway → Appointment Service.
4. Appointment Service:
   - Gọi **Customer service** để lưu thông tin khách hàng
   - Gọi **Scheduling service** để thực hiện lưu thông tin lịch.
   - Nếu đặt thành công: lưu thông tin vào cơ sở dữ liệu.
   - Gọi **Notification Service** để gửi email xác nhận.
---
## 📁 Folder Structure

```
mid-project-422482494/
├── README.md                       # File hướng dẫn
├── .env.example                    # Gitignore
├── docker-compose.yml              # Docker compose cho tất cả service và frontend
├── docs/                           # Thư mục tài liệu
│   ├── architecture.md             # Kiến trúc hệ thống
│   ├── analysis-and-design.md      # Phân tích và thiết kế chi tiết các service
│   ├── asset/                      # Lưu trữ các ảnh và diagram
│   └── api-specs/                  # Đặc tả API sử dụng OpenAPI (YAML)
│       ├── service-a.yaml
│       └── service-b.yaml
├── scripts/                        # Frontend của dự án
│   ├── init.sh                  
│   └── front-end                   # Thư mục gốc của frontend
├── services/                       # Các service của hệ thống
│   ├── appointment-service/
│   │   ├── Dockerfile
│   │   ├── src/
│   │   └── readme.md               # Mô tả cho appointment service
│   ├── scheduling-service/
│   │   ├── Dockerfile
│   │   ├── src/
│   │   └── readme.md               # Mô tả cho scheduling service
│   ├── employee-service/
│   │   ├── Dockerfile
│   │   ├── src/
│   │   └── readme.md               # Mô tả cho employee service
│   └── ├── notification-service/
│       ├── Dockerfile
│       ├── src/
│       └── readme.md               # Mô tả cho notification service
└── gateway/                        # API Gateway
    ├── Dockerfile
    └── src/
```

---
## 🧱 Kiến trúc vi dịch vụ

| Microservice              | Mô tả                                                     |
|---------------------------|-----------------------------------------------------------|
| `service-catalog-service` | Quản lý các danh mục dịch vụ                              |
| `employee-service`        | Quản lý nhân viên, lịch làm việc                          |
| `schedule-service`        | Quản lý lịch hẹn                                          |
| `appointment-service`     | Tiếp nhận yêu cầu đăng ký lịch hẹn, gửi yêu cầu thông báo |
| `notification-service`    | Gửi thông báo đến người dùng                              |
| `customer-service`        | Quản lý thông tin khách hàng                              |
| `gateway`                 | API Gateway định tuyến                                    |
| `frontend`                | Giao diện người dùng với React                            |

## 🛠️ Công nghệ sử dụng

- **Spring Boot** Cho các service backend
- **React, TypeScript, Tailwind CSS** cho frontend
- **MySQL, Redis** cho lưu trữ dữ liệu
- **Pessimistic Lock** cho xử lý xung đột dữ liệu
- **RabbitMQ** cho message broker (hệ thống trung gian gửi nhận tin nhắn)
- **Docker** để triển khai toàn bộ hệ thống
- **SMTP** cho gửi email thông báo.

## ⚙️ Hướng dẫn cài đặt hệ thống với Docker

### 1. Clone source code

   ```bash
   git clone https://github.com/VietHungHoang/BookAppointmentService.git
   cd BookAppointmentService
   ```

### 2. Run with Docker Compose

   ```bash
   docker-compose up --build
   ```
---
