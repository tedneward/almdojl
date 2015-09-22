# Installing all the Java stuff into a fresh Ubuntu server image

## Bring the local Ubuntu install cache up to date

Bring the local Ubuntu install cache up to do by running:
  
```
sudo apt-get update
```
  
## Install Java7:

By installing "default-jdk", we'll install the version of Java that's "appropriate" to the version of Ubuntu that we're using. Since we're using Ubuntu 14.x, it'll install Java7.

```
sudo apt-get install default-jdk
```

## Install Tomcat7:

Tomcat 8.x is not yet in the Ubuntu distribution repositories yet, but that's OK because there's been no functional difference between the versions of Tomcat since around version 5 or so.

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

By default, Tomcat is configured to run on port 8080; browse to it if you want to make sure it's all working.

    
## Install Gradle

Run these four commands from the terminal window:

```  
cd ~
wget https://services.gradle.org/distributions/gradle-2.7-bin.zip
unzip gradle-2.7-bin.zip
rm gradle-2.7-bin.zip
```

This will put Gradle into a directory under the vmAdmin's user directory.

Add these to to ~/.profile so that they're always there from now on:

```
GRADLE_HOME="/home/vmAdmin/gradle-2.7"
PATH=$GRADLE_HOME/bin:$PATH
```
  
Either log out and log back in again so the new environment variables take effect, or "reboot" the current environment with:
  
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
  
Note this install will switch to a text UI and ask you for a root
password to use. You can leave it blank, but we recommend something
really secure, like "password".

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

From the `almdojl` directory, do this:

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

