
jQuery.extend({

    createUploadIframe: function(id, form, uri)
	{
			//create frame
            var frameId = 'jUploadFrame' + id;
            var io ;
            if(window.ActiveXObject) {
                io = $('<iframe id="' + frameId + '" name="' + frameId + '" />');
                if(typeof uri== 'boolean'){
                    $(io).attr("src","javascript:false");
                }
                else if(typeof uri== 'string'){
                    $(io).attr("src",uri);
                }
            }
            else {
                io = $('<iframe id="' + frameId + '" name="' + frameId + '" />');
                $(io).attr("id",frameId);
                $(io).attr("name",frameId);
            }
            $(io).css("position","absolute");
            $(io).css("top","-1000px");
            $(io).css("left","-1000px");

            $(io).appendTo("body");

            return io			
    },
    createUploadForm: function(id, fileElements, paramEles)
	{
		//create form	
		var formId = 'jUploadForm' + id;
		var fileId = 'jUploadFile' + id;
		var form = $('<form  action="" method="POST" name="' + formId + '" id="' + formId
            + '" enctype="multipart/form-data" encoding="multipart/form-data"  ></form>');
		// var oldElement = $('#' + fileElementId);
		// var newElement = $(oldElement).clone();
		// $(oldElement).attr('id', fileId);
		// $(oldElement).before(newElement);
		// $(oldElement).appendTo(form);
		$(fileElements).each(function(index,obj){
            $(obj).removeAttr('form');
            $(obj).appendTo(form);
        });

        $.each(paramEles, function (index, field) {
            $('<input type="hidden"/>')
                .prop('name', index)
                .val(field)
                .appendTo(form);
        });

		//set attributes
		$(form).css('position', 'absolute');
		$(form).css('top', '-1200px');
		$(form).css('left', '-1200px');
		$(form).appendTo('body');		
		return form;
    },

    ajaxFileUpload: function(s) {
        // TODO introduce global settings, allowing the client to modify them for all requests, not only timeout
        s = jQuery.extend({}, jQuery.ajaxSettings, s);
        s.secureuri=false;
        var id = s.fileElementId;        
		var form = jQuery.createUploadForm(id, s.fileEles, s.paramDatas);
        var fileFormData = new FormData($(form)[0]);

        var io = jQuery.createUploadIframe(id, formId,s.secureuri);
        var frameId = 'jUploadFrame' + id;
        var formId = 'jUploadForm' + id;
        var xmr;
        try
        {
			var form = $('#' + formId);
			$(form).attr('action', s.url);
			$(form).attr('method', 'POST');
			$(form).attr('target', frameId);
            if(form.encoding)
			{
                form.encoding = 'multipart/form-data';
            }
            else
			{
                form.enctype = 'multipart/form-data';
            }

            $.ajax({
                url: s.url,
                type: 'POST',
                async:false,
                data: fileFormData,
                processData: false,
                contentType: false,
                beforeSend:function(XHR){
                    xmr=XHR;
                },
                success:function(DATA,STATUS,XMR){
                    xmr=XMR;
                    if ( s.success )
                        s.success( DATA,XMR, STATUS );
                },
                complete:function(XMR, status){
                    xmr=XMR;
                    if ( s.complete )
                        s.complete(XMR, status);
                },
                error:function(XMR,ERRORMSG,ERROR){
                    xmr=XMR;
                    if ( s.error ) {
                        s.error(XMR,ERRORMSG,ERROR);
                    }
                }
            });
            setTimeout(function()
            {	try
            {
                $(io).remove();
                $(form).remove();

            } catch(e)
            {
                jQuery.handleError(s, xmr, null, e);
            }

            }, 100)
        } catch(e)
        {
            jQuery.handleError(s, xmr, null, e);
        }
        return {abort: function () {}};

    },

    uploadHttpData: function( r, type ) {
        var data = !type;
        data = type == "xml" || data ? r.responseXML : r.responseText;
        // If the type is "script", eval it in global context
        if ( type == "script" )
            jQuery.globalEval( data );
        // Get the JavaScript object, if JSON is used.
        if ( type == "json" )
            eval( "data = " + data );
        // evaluate scripts within html
        if ( type == "html" )
            jQuery("<div>").html(data).evalScripts();
			//alert($('param', data).each(function(){alert($(this).attr('value'));}));
        return data;
    },
    handleError: function( s, xhr, status, e )      {
        // If a local callback was specified, fire it
        if ( s.error ) {
            s.error.call( s.context || s, xhr, status, e );
        }

        // Fire the global callback
        if ( s.global ) {
            (s.context ? jQuery(s.context) : jQuery.event).trigger( "ajaxError", [xhr, s, e] );
        }
    }
})

