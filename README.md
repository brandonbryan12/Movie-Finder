# Movie Finder

Movie Finder is a containerized server application that interacts with themoviedb.org to enable:

  - Searching for movies by query
  - Searching for movies by id
  - Storing movie metadata into an in-memory DB (H2)

# Installation and Run!
  - Add your API key in application.yml to this field: apiKey
## Maven
```sh
$ mvn clean install
```

## Docker
```sh
docker build -t moviefinder .
```

## Run
```sh
docker run -p 8080:8080 moviefinder
```

# Endpoints
  - GET localhost:8080/movie/search?query={QUERY}
  - GET localhost:8080/movie/{id}
