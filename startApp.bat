set JAVA_HOME="C:\Installers\Java\jdk1.8.0_152"
set PATH=C:\Installers\Java\jdk1.8.0_152\bin;C:\Installers\Maven\apache-maven-3.5.3\bin;%PATH%

net start mysql80
mysql -u petadmin --password=petadmin -D petstoredb -e "select * from Pet"

cd C:\My\Programming\workspace\PetStoreApp
mvnw spring-boot:run

pause