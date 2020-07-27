# richer-insurance-tracker
Execute through commandline
- java -jar insurance-tracker-0.0.1.jar

Execute through command line with vendor specific configuration
- java -jar insurance-tracker-0.0.1.jar --spring.config.location=application-remote.yml

Schedule cron job 
- cron: 0/60 * * * * *   # Run every minute
- cron: 0/5 * * * * *    # Run every 5 minutes
- cron: 0 30 10 * * ?    # Run 10:30 everyday of every month

Post Vehicle Record - http://localhost:8080/add-vehicle

{  
"registrationNumber": "6Vb-101",  
"make": "Honda",  
"model": "truck"  ,
"year": "2020"  ,
"description": "myfirst"  
}  


Post VehicleInsurance Records - http://localhost:8080/add-vehicle-insurance

{  
"registrationNumber": "6Vb-102",  
"policyNumber": "89784687101",  
"startDate": "2020-07-20"  ,
"endDate": "2020-07-28"    
}  

