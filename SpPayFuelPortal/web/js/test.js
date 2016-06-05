/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function LoadSubView(url) {
    
           
    $('#targetDiv').load(url, function (response, status) {
        if (status === "success") {
            alert("loaded");
        }
        else{
            alert("failed to load");
        }
    });
           
}          
            


