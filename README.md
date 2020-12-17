# mirtapp-back

Mirtapp is a web application developed for small business, the main goal is to store shopping lists and suggest products that may be useful to buy. 
In order to not raise the limit of database storage of the free hosting, the app will be available for a single group of authorized people

## To run it
- Clone this repository
- Make `mvn spring-boot:run` in the project root folder.

## To deploy it 
- `heroku login`
- `heroku git:remote -a mirtapp-back`
- `git push heroku master`
