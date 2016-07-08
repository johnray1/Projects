jQuery(document).ready(function($) {

		
    $('a[data-rel="lightcase"]').lightcase({
        transition: 'scrollTop',
        speedIn:'600',
        speedOut:'600',
        forceWidth:true,
        swipe: true,
        useKeys: true,
        overlayOpacity:0.8,
        title:'',
        showSequenceInfo: false,
        iframe: {
            width:'450',
            height:'580',
            frameborder: 0
        }

    });
		
    $('a[data-rel="lightcase_edit"]').lightcase({
        transition: 'scrollTop',
        speedIn:'600',
        speedOut:'600',
        forceWidth:true,
        swipe: true,
        useKeys: true,
        overlayOpacity:0.8,
        title:'',
        showSequenceInfo: false,
        iframe: {
            width:'450',
            height:'480',
            frameborder: 0
        }
                        
                        

    });
                
    $('a[data-rel="lightcase:myCollection"]').lightcase({showSequenceInfo: false});

    $('a[data-rel="lightcase_forgot"]').lightcase({
        transition: 'scrollTop',
        speedIn:'600',
        speedOut:'600',
        forceWidth:true,
        swipe: true,
        useKeys: true,
        overlayOpacity:0.8,
        title:'',
        showSequenceInfo: false,
        iframe: {
            width:'450',
            height:'280',
            frameborder: 0
        }

    });
                
    $('#various9').click(function (event) {
        event.preventDefault();

        lightcase.start({                                
            href: '#',
            maxWidth: 640,
            maxHeight: 400,
            onFinish: {
                function () {
                    
                    $.ajax({
                        type: "GET",
                        url: $('#various9').attr('href'),
                        error: function (xhr, status, error) {

                            if (xhr.status == 400) { 
                                alert("item already deleted")
                                return xhr.status;
                            }
                            else {
                                alert(error + ":" + status)
                            }
                        },
                        success: function (response) {

                            lightcase.get('contentInner').children().html(response);
                            // Do a resize now after filling in the content                             
                            lightcase.resize();
                        }
                    });
                }
            }
        });
    });
});
