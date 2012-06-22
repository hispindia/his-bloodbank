CKEDITOR.plugins.add( 'templateinfo', {
	init: function( editor ){
		editor.addCommand( 'inserttemplateinfo',{
			exec : function( editor ) {    						
				var data = "template info";
				editor.insertHtml(data)
			}
		});
		
		editor.ui.addButton( 'templateinfo', {
			label: 'Insert templateinfo',
			command: 'inserttemplateinfo',
			icon: this.path + 'images/templateinfo.png'
		});
	}
});
