function intercitynavigation()
{
 //   $(document).ready(function(){
    
    var url = "http://localhost:8080/Famoco/busesServlet";
    $.ajax({
        type: "POST",
        url: url,
         success: function(xml){
       //   var nextpage =@Session["nexpage"];
            alert(" the serlvet invocation has been successful");
            $("#mycontent").load("faces/buses.xhtml"); 
        }
    }) ;  
        
        
       
 //   }); 
    
}
