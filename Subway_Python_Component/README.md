# Hong Kong Subway Navigator

## About this project
This Hong Kong Subway Navigator project is used to help users be able to navigate through the Hong Kong Subway System. Enjoy using this project! 


## Usages
There are two major features: the ability to **find the optimal path between two stations** and to **communicate between other users on the website through the announcements feature**.
- **Optimal Path**: Use the dropdown to select two stations to find the best way to travel. THe stations will be sorted in alphabet order. 
- **Announcements**: Send announcements, update past announcements, or delete past announcements in real time! This is used to help communicate information!

## How This Project was Built 

This project uses Java Spring Boot to create an API endpoint for the result from the path-finding between two stations. As for frontend, this uses Flask-SocketIO with Python, while using Supabase to maintain a database for past announcements. I have also used Colab Google with Google Spreadsheet (the datainsert folder) for writing the java lines faster. There are also comments in the code if you would like to see some of the logs printed out in the console. Feel free to check those out too!

## How to Host the Website Locally
- First, use `git clone https://github.com/CosmoWuu1579/hkSubwayNavi.git` to download the files
- Download the needed dependencies `pip install -r requirements.txt`
- Change .env.example to .env and change the variables to store your keys for the Supabase and the Flask secret key.
- Next, first run the SubwayNaviApplication.java to get the API endpoint.
- Run the app.py file and follow the terminal instructions to open the website!

## Credits
This project uses information from the Wikipedia page of the [Hong Kong MTR Station](https://en.wikipedia.org/wiki/List_of_MTR_stations). Feel free to check it out!

   