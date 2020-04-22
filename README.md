# Five In A Row MMR Server

## About

The purpose of this project is to evaluate the TrueSkill "MMR" system used in Halo 2. The goal is to match players against equal skill level as well as track MMR, played games, wins, losses and making these stats available to display leaderboards. 

The game chosen was Five In A Row since it is non-trivial. The server uses a queue system to match appropriate players with each other.

It uses the following to calculate MMR. https://github.com/nikalsh/mmr 

Read more https://web.archive.org/web/20070313234742/http://www.bungie.net/Stats/page.aspx?section=FAQInfo&subsection=stats&page=statoverview

Read more 

## Prerequisites
- Java 11
- Maven

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Demo

https://fivexo.herokuapp.com/
