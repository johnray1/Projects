         jQuery(document).ready(function( $ ) {
            $("#menu").mmenu({
               "extensions": [
                  "effect-panels-zoom"
               ],
               "iconPanels": true,
               "navbars": [
                  {
                     "position": "top"
                  },
                  {
                     "position": "bottom",
                     "content": [
                    "<div class='mfooter'><strong>Web App  version 1.03 build 150916</strong><br />&copy; Copyright 2016 -  ENGEN Petroleum -  All Rights Reserved <br /><a href='#'><img src='images/poweredbyoltranz.png'></a></div>"
                     ]
                  }
               ]
            });
         });