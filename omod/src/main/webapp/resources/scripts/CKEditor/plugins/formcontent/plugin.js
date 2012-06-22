CKEDITOR.plugins.add( 'formcontent', {
	init: function( editor ){
		editor.addCommand( 'insertformcontent',{
			exec : function( editor ) {    						
				var data = "<div id='formcontent' title='form content'><input disabled='disabled' style='width:200px; border:2px solid red; text-align: center;' value='&lt;&lt;&lt; FORM CONTENT &gt;&gt;&gt;'/></div>";
				editor.insertHtml(data);
			}
		});
		
		editor.ui.addButton( 'formcontent', {
			label: 'Insert formcontent',
			command: 'insertformcontent',
			icon: this.path + 'images/formcontent.png'
		});
	}
});