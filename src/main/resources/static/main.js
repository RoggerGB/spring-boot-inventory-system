/**
 * 
 */
 $('document').ready(function()
 {
 	$('.table .btn').on('click',function(event)
 	{
 		event.preventDefault();
 		var href = $(this).attr('href');
 		$.get(href,function(ordenProducto,status)
 		{
 			$('productoEdit').val(ordenProducto.producto);
 			$('cantidadEdit').val(ordenProducto.cantidad);
 			$('precioEdit').val(ordenProducto.precio);
 		});
 		
 		
 		$('#modalEdit').modal();
 	});
 });