# Rest Service Api

## User Api
/api/users

## Endpoints
1. ### Get User By ID
   * URL: `/api/users?id={userId}`
   * Method: `GET`
   * Query Parameters:
       * `id` (required): The unique identifier of the user.
   * Success Response:
       * Code: `200 OK`
       * Content:

         [{
         "id": 1,
         "name": "John Doe",
         "email": "john.doe@example.com",
         "roles": null
         }
         ]
     
   * Error Response
      * Code: `404 Not Found`
      * Content:

        "User not found"
   
2. ### Get All Users
   * URL: `/api/users`
   * Method: `GET`
   * Success Response:
     * Code: `200 OK`
     * Content:
     
       [
       {
       "id": 1,
       "name": "John Doe",
       "email": "john.doe@example.com",
       "roles": null
       },
       {
       "id": 2,
       "name": "Jane Smith",
       "email": "jane.smith@example.com",
       "roles": null
       }
       ]
     
3. ### Create User
   * URL: `/api/users`
   * Method: `POST`
   * Request Body:
     * Content-Type: `application/json`
       * Body:
         
         {
         "name": "John Doe",
         "email": "john.doe@example.com"
         }

   * Success Response:
     * Code: `201 Created`
     * Content:

       {
       "id": 1,
       "name": "John Doe",
       "email": "john.doe@example.com",
       "roles": null
       }
     
   * Error Responses:
        * Code: `400 Bad Request`
            * Content:

              "Internal server error"
          
4. ### Delete User
    * URL: `/api/users?id={userId}`
    * Method: `DELETE`
    * Query Parameters:
        * `id` (required): The unique identifier of the user to delete.
    * Success Response:
        * Code: `204 No Content`
    * Error Responses:
        * Code: `404 Not Found`
            * Content:

              "User not found"
        * Code: `400 Bad Request`
            * Content:

              "User ID is required"
         
## Role Api

## Base URL

/api/roles

## Endpoints

1. Get Role by ID
    * URL: `/api/roles?id={roleId}`
    * Method: `GET`
    * Query Parameters:
        * `id` (required): The unique identifier of the role.
    * Success Response:
        * Code: `200 OK`
        * Content:

          {
          "id": 1,
          "roleName": "ADMIN"
          }
      
    * Error Response:
        * Code: `404 Not Found`
        * Content:

          "Role not found"
      
2. Get All Roles
    * URL: `/api/roles`
    * Method: `GET`
    * Success Response:
        * Code: `200 OK`
        * Content:

          [
          {
          "id": 1,
          "roleName": "USER"
          },
          {
          "id": 2,
          "roleName": "ADMIN"
          }
          ]  
      
3. Create Role
    * URL: `/api/roles`
    * Method: `POST`
    * Request Body:
        * Content-Type: `application/json`
        * Body:

          {
          "roleName": "MODERATOR"
          }
    * Success Response:
        * Code: `201 Created`
        * Content:

          {
          "id": 3,
          "roleName": "MODERATOR"
          }
    * Error Responses:
      * Code: `400 Bad Request`
        * Content:

          "Role name is required"
        
    * Code: `500 Internal Server Error`
      * Content:
      
            "Internal server error"
    
4. Delete Role
    * URL: `/api/roles?id={roleId}`
    * Method: `DELETE`
    * Query Parameters:
      * `id` (required): The unique identifier of the role to delete.
    Success Response:
      * Code: `204 No Content`
    Error Responses:
      * Code: `404 Not Found`
        * Content:

          "Role not found"
      * Code: 400 Bad Request
        * Content:

          "Role ID is required"