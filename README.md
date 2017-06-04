# Web-Application-Projects
School Projects

MediFind Data Driven Dynamic Web Application
 
 Framework
 
                          [ Web Browswer ]
      
           [Model]                                [View]
    [HTTP Serverlet | Controller ]            [ JSP Pages ]
  
                            [ Model ]
                        [Oracle Database ]
                        


Sample E commerce site - Building Scalable Distributed Systems

Framework
Classic Architecture for web applications 
     [ Web Browser ] [ Application Server ] [ Database ] 
Simple Distribute Architecture 
     [ Web Browser ]  || [ Application Server Cluster, Memcache Cluster Session Management || [ RDS Database Cluster ] 
Intermediate Distributed 
    [ Web Browser ]  || [ Application Server Cluster, Memcache Cluster Session Management, Load Balancer, Auto Scaling policy  ||  [ RDS Database Cluster ]
    
    
Web Application Code 
- Implemented in JavaScript in Node js. 
- Stateless server implementation
- Server instance in Node js, using express. 
- Based on REST API, http post methods. 
- HHTP/post {arguments}, HHTP response {return }

Hosting 
- Application code, hosted on AWS EC2 instance, running node.js and listening for http requests 



  
 
