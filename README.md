# Sidorov-KA-Java-2021-Liga
## Команда для запуска контейнером с постгрес
```
 docker run —name db1-13.3 -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=db1 -d
postgres:13.3
```
