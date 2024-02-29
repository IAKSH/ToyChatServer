# ToyChatServer
An anonymous online chat room server, and then, a toy.

This is merely my try out on Spring MVC and Vue3.

Don't treat this a real chat server!

# Usage
1. compile spring server
```
./spring/gradlew shadowJar
```
2. build vue3 web UI
```
cd webui
npm run build
```
3. run docker compose
```shell
docker compose up -d
```