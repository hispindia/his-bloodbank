CKEDITOR.plugins.add( 'forminfo', {
	init: function( editor ){
		editor.addCommand( 'insertforminfo',{
			exec : function( editor ) {    						
				var data = "<div id='forminfo' title='form content'><input disabled='disabled' style='width:200px; border:2px solid red; text-align: center;' value='&lt;&lt;&lt; TEST INFORMATION &gt;&gt;&gt;'/></div>";
				editor.insertHtml(data);
			}
		});
		
		editor.ui.addButton( 'forminfo', {
			label: 'Insert forminfo',
			command: 'insertforminfo',
			icon: this.path + 'images/forminfo.png'
		});
	}
});