CKEDITOR.plugins.add( 'formradio', {
	init: function( editor ){
		editor.addCommand( 'insertformradio',{
			exec : function( editor ) {    						
				tb_show("testing", "selectObsPopup.form?type=radio&modal=true&height=120&width=500");
			}
		});
		
		editor.ui.addButton( 'formradio', {
			label: 'Insert formradio',
			command: 'insertformradio',
			icon: this.path + 'images/formradio.png'
		});
	}
});
