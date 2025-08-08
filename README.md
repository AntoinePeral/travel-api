New readme
Quand on lance les test remettre user entre guillemet INSERT INTO "user" afin que les tests fonctionnent pour h2.


Lancer en local

docker compose -f docker-compose.dev.yml up --build -d
# logs si besoin
docker compose -f docker-compose.dev.yml logs -f app
# stopper
docker compose -f docker-compose.dev.yml down
# stopper + wipe DB dev
docker compose -f docker-compose.dev.yml down -v

Should no push on docker image just test 