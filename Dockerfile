FROM clojure:temurin-17-tools-deps-bullseye
COPY . /app
WORKDIR /app
RUN apt-get update && apt-get install -y curl
CMD ["-M:start"]
