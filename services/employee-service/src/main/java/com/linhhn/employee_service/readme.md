# Employee Service

## Overview
Dịch vụ Employee Service quản lý thông tin nhân viên, bao gồm:
- Quản lý nhân viên theo dịch vụ
- Cung cấp danh sách nhân viên theo dịch vụ và lịch làm việc
- Truy vấn nhân viên rảnh theo lịch và thời gian

Dịch vụ này giúp hỗ trợ các dịch vụ khác như Appointment, Scheduling trong việc phân công và điều phối nhân viên.

## Setup
- Built using the provided Dockerfile.
- Source code is in the src/ folder.

## Development
- Định nghĩa API trong docs/api-specs/employee-service.yaml.
- Run locally via docker-compose up --build from the root directory.

## Endpoints
- Base URL: http://localhost:8082/
- Tham khảo chi tiết API trong docs/api-specs/employee-service.yaml.