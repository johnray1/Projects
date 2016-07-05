
// XEDITABLE
$(document).ready(function() 
{
    //$.fn.editable.defaults.url = '/post'; 
    $.fn.editable.defaults.mode = 'inline'; 
    
    //enable / disable
    $('#enable').click(function() {
        $('.editable').editable('toggleDisabled');
    });  
    
    //make data1 editable
    $('#userid').editable();
    
    //make data1 editable
    $('#firstname').editable();
    
    //make data1 editable
    $('#othername').editable();
    
    //make email editable
    $('#email').editable();
    
    //make email editable
    $('#genderto').editable();
    
    //make data2 selectbox editable
    $('#gender').editable({
        type: 'select',
        value: 2, //display default value
        source: [
            {value: 1, text: 'MALE'},
            {value: 2, text: 'FEMALE'}
        ]
        
        /*
			//uncomment these lines to send data on server
			,pk: 1
			,url: '/post'
         */
    });
    
    //make data1 editable
    $('#phone').editable(); 
    
    //make data1 editable
    $('#details').editable();	
    
    
    
    
    
    //make users checkbox editable
    $('#users').editable({
        limit: 4,
        source: [
            {value: 1, text: 'User 1'},
            {value: 2, text: 'User 2'},
            {value: 3, text: 'User 3'},
            {value: 4, text: 'User 4'},
        ]
    });
    
    //make roles checkbox editable
    $('#roles').editable({
        limit: 4,
        source: [
            {value: 1, text: 'Role 1'},
            {value: 2, text: 'Role 2'},
            {value: 3, text: 'Role 3'},
            {value: 4, text: 'Role 4'},
        ]
    });
    
    
    //make permission checkbox editable
    $('#permissions').editable({
        limit: 5,
        source: [
            {value: 1, text: 'Permission 1'},
            {value: 2, text: 'Permission 2'},
            {value: 3, text: 'Permission 3'},
            {value: 4, text: 'Permission 4'},
            {value: 5, text: 'Permission 5'},
            {value: 6, text: 'Permission 6'},
            {value: 7, text: 'Permission 7'},
            {value: 8, text: 'Permission 8'},
            {value: 9, text: 'Permission 9'},
            {value: 10,text:'Permission 10'}
        ]
    });
    
    
});
