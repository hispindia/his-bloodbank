/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	config.extraPlugins = 'formtextbox,formradio,formselection,formcontent,forminfo,date';
	config.toolbar =[
		[ 'Source','-','Save','NewPage','DocProps','Preview','Print','-','Templates' ],
		[ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ],
		[ 'Find','Replace','-','SelectAll','-','SpellChecker', 'Scayt' ],		
			'/',
		[ 'Bold','Italic','Underline','Strike','Subscript','Superscript','-','RemoveFormat' ],
		[ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote','CreateDiv','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','BidiLtr','BidiRtl' ],
		[ 'Link','Unlink','Anchor' ],
		[ 'Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak','Iframe' ],
			'/',
		[ 'Styles','Format','Font','FontSize' ],
		[ 'TextColor','BGColor' ],
		[ 'Maximize', 'ShowBlocks','-','About' ],
		'/',
		['formtextbox', 'formradio', 'formselection', 'formcontent', 'forminfo' , 'date']
	];
};
