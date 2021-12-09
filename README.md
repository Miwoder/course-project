**Project from courses**

The main idea is to create platform where traders can post their game objects from many game.
 Also users can leave comments to traders. 
 
All actions can only be done by authorized users.

Game objects and comments can be deleted and edited only by the owner.

Administrator can approve new traders and comments.

Users can add new trader if they don't see one.

Main endpoints:

#### `Comment service:`

| Method        | Path                            | Description                  |
| ------------- |:-------------------------------:|:----------------------------:|
| POST          | /users/{id}/comments            | add new comment for user     |
| GET           | /users/{id}/comments/{commentId}| get comment for user         |
| GET           | /users/{id}/comments            | get all comments for user    |
| DELETE        | /users/{id}/comments/{commentId}| delete comment               |
| PUT           | /users/{id}/comments/{commentId}| edit comment                 |

#### `User service:`

| Method        | Path                            | Description                  |
| ------------- |:-------------------------------:|:----------------------------:|
| POST          | /signup                         | add new user                 |
| GET           | /signin                         | sign in                      |
| POST          | /authentication/forgot_password | send code to email           |
| POST          | /authentication/reset           | reset password with code     |
| GET           | /traders                        | get all traders              |
| POST          | /traders                        | add new trader               |

#### `Game object service:`

| Method        | Path                                | Description                  |
| ------------- |:-----------------------------------:|:----------------------------:|
| GET           | /users/{id}/objects                 | get all users game objects   |
| GET           | /my                                 | get all game objects for authorized user            |
| POST          | /my                                 | add new game object          |
| PATCH         | /users/{traderId}/objects/{objectId}| edit game object             |
| DELETE        | /users/{traderId}/objects/{objectId}| delete game object           |

#### `Game service:`

| Method        | Path                                | Description                  |
| ------------- |:-----------------------------------:|:----------------------------:|
| GET           | /games                              | get all games                |
| POST          | /games/new                          | add new game                 |
| PUT           | /games/{id}/edit                    | edit game                    |

#### `Admin service:`

| Method        | Path                                | Description                  |
| ------------- |:-----------------------------------:|:----------------------------:|
| GET           | /administration/comments            | get all unconfirmed comments |
| GET           | /administration/users               | get all unconfirmed users    |
| PATCH         | /administration/comments/{id}       | approve comment              |
| PATCH         | /administration/users/{id}          | approve trader               |
| DELETE        | /administration/comments/{id}       | decline commen               |
| DELETE        | /administration/users/{id}          | decline trader               |
