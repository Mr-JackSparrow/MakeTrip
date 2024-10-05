CREATE TABLE IF NOT EXISTS users (
    "userId" SMALLSERIAL PRIMARY KEY NOT NULL,
    "firstName" VARCHAR(50) NOT NULL,
    "lastName" VARCHAR(50) NOT NULL,
    "mobileNo" VARCHAR(50) NOT NULL,
    "emailId" VARCHAR(50) NOT NULL,
    "createdAt" TIMESTAMP NOT NULL,
    "updatedAt" TIMESTAMP NOT NULL,
    address VARCHAR(150) NOT NULL,
    location GEOMETRY(Point, 4326) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS trips (
    "tripId" SMALLSERIAL PRIMARY KEY NOT NULL,
    "tripMakerId" INT NOT NULL REFERENCES users("userId"),
    "tripDestinationDescription" VARCHAR(150) NOT NULL,
    "startDate" DATE NOT NULL,
    "endDate" DATE NOT NULL,
    "maxParticipants" INT NOT NULL,
    "createdAt" TIMESTAMP NOT NULL,
    "updatedAt" TIMESTAMP NOT NULL,
    location GEOGRAPHY(Point, 4326) NOT NULL
);

CREATE TABLE IF NOT EXISTS "tripParticipants" (
    id SMALLSERIAL PRIMARY KEY NOT NULL,
    "tripId" INT REFERENCES trips("tripId") NOT NULL,
    "userId" INT REFERENCES users("userId") NOT NULL,
    "joinedAt" TIMESTAMP NOT NULL
);