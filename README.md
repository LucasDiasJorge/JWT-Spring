
## API routes

#### Login

```http
  Post http://localhost:8080/login
```
+ Request (application/json)

    + Body

            {
              "email": "personal@email.com",
              "pass": "somehashorsomething123"
            }

+ Response 200 (application/json)

    + Body

            {
                "Token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.
                eyJhcHAiOiIzNDhlNTM4MzQ2M2Y3MzZjMGExZTJhNTFmNjYwZjA5NCIsInN1YmRvbWluaW8iOiJleGVtcGxvIiwiY2xpZW50IjoiMTU0ZDZlZGQ4YmQzMDEwYzQ4NjBkN2E5Y
                zk1NzNmYmVmZTUyNGRlZiJ9.JJNs0bFtGOtwyJy_r-eefsvkd387M_x7zpucE1m4WIw"
            }
