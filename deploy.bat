heroku login
heroku deploy:jar target/acts-reconciliation.jar -o --server.port=$PORT --spring.datasource.username=DB_USERNAME --spring.datasource.password=DB_PASSWORD --spring.datasource.url=DB_URL