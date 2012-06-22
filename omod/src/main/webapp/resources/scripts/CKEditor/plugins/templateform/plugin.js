CKEDITOR.plugins.add( 'templateform', {
	init: function( editor ){
		editor.addCommand( 'inserttemplateform',{
			exec : function( editor ) {    						
				var data = "templateform";
				editor.insertHtml(data)
			}
		});
		
		editor.ui.addButton( 'templateform', {
			label: 'Insert form content',
			command: 'inserttemplateform',
			icon: this.path + 'images/templateform.png'
		});
	}
});
