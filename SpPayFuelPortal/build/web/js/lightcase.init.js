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
                
                
                $('a[data-rel="lightcase_userdetails"]').lightcase({
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
				width:'800',
				height:'680',
				frameborder: 0
			}

		});

	});
