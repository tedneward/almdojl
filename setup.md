# Installing all the Java stuff into a fresh Ubuntu server image

## Bring the local Ubuntu install cache up to date

Bring the local Ubuntu install cache up to do by running:
  
```
sudo apt-get update
```
  
## Install Java7 (depending on Ubuntu version):

```
sudo apt-get install default-jdk
```

## Install Tomcat7 (Tomcat8 not in the Ubuntu package distros yet):

```
sudo apt-get install tomcat7
```

Configure the Tomcat7 manager GUI to have a user/password to use:

```
sudo nano /etc/tomcat7/tomcat-users.xml
```

Make sure it has the "<user ...>...</user>" below in it

```xml
<tomcat-users>
  <user username="admin" password="password" roles="manager-gui,admin-gui" />
</tomcat-users>
```

Restart Tomcat to get those changes to take effect

```
sudo service tomcat7 restart
```

By default, Tomcat is configured to run on port 8080

    
## Install Gradle

Run this:

```  
cd ~
wget https://services.gradle.org/distributions/gradle-2.7-bin.zip
unzip gradle-2.7-bin.zip
rm gradle-2.7-bin.zip
```

Add these to to ~/.profile:

```
GRADLE_HOME="/home/vmAdmin/gradle-2.7"
PATH=$GRADLE_HOME/bin:$PATH
```
  
Either log out or "reboot" the current environment with:
  
```
source .profile
```

... from the user's home directory ("cd ~" first)

  
## Install git

```
sudo apt-get install git
```

## Install MySQL 5.6:

```
sudo apt-get install mysql-server-5.6
```
  
(Note this install will switch to a text UI and ask you for a root
password to use. You can leave it blank, but we recommend something
really secure, like "password".)

After MySQL is installed and running, connect to it to create the user
that we use from the Gradle script and Java code:

```
mysql -uroot -p
<enter password>
CREATE USER 'user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'user'@'localhost';
```

Check to see what users are in MySQL already

```
SELECT User,Host FROM mysql.user;
```

## Git clone the repo

```
git clone https://github.com/tedneward/almdojl.git almdojl
``` 
## Create and seed the database

```
gradle createDB seedDB
```

## Build the WAR file

```
gradle war
```

## Deploy the WAR file

Either do it by hand:

```
sudo cp build/libs/almdojl.war /var/lib/tomcat7/webapps
```  
Or (experimental) let Gradle do it:

```
sudo gradle deploy
```

## Add some fares to the database

```
gradle addFares
```

## Enjoy

