CKEDITOR.plugins.add( 'timestamp', {
	init: function( editor ){
		editor.addCommand( 'insertTimestamp',{
			exec : function( editor ) {    
				var timestamp = new Date();				
				tb_show("testing", "selectObsPopup.form?modal=true&height=200&width=800");
			}
		});
		
		editor.ui.addButton( 'Timestamp', {
			label: 'Insert Timestamp',
			command: 'insertTimestamp',
			icon: this.path + 'images/timestamp.png'
		});
	}
});