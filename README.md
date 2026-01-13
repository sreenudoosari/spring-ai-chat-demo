# Demo project for Spring Boot AI Chat application with docker model runners


### Create OPEN AI KEY
https://platform.openai.com/settings/organization/api-keys

### Create GEMINI AI KEY
https://aistudio.google.com/app/api-keys

### Command to check the env variables in Windows PowerShell
[Environment]::GetEnvironmentVariables().Keys | Select-String KEY

### build jar
mvn clean package

### build image
docker build -t sdoos/spring-ai-chat-app .

### verify image
docker images

### Run Docker Compose using the file path
docker compose -f docker/compose.yml up

### detached mode
docker compose -f docker/compose.yml up -d