# Service Notification

## Overview
Dịch vụ Notification Service quản lý việc gửi các thông báo cho khách hàng qua email. Các thông báo bao gồm:
- Thông báo đặt lịch hẹn thành công

Dịch vụ này giúp hệ thống tự động thông báo cho khách hàng và nâng cao trải nghiệm người dùng.

## Setup
- Built using the provided `Dockerfile`.
- Source code is in the `src/` folder.

## Development
- Định nghĩa API trong `docs/api-specs/service-a.yaml`.
- Run locally via `docker-compose up --build` from the root directory.

## Endpoints
- Base URL: `http://localhost:5001/`
- Refer to `docs/api-specs/service-a.yaml` for API details.
