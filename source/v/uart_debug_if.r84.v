


















































































`include "timescale.v"


`include "uart_defines.v"

module uart_debug_if (

wb_dat32_o,

wb_adr_i, ier, iir, fcr, mcr, lcr, msr,
lsr, rf_count, tf_count, tstate, rstate
) ;

input [`UART_ADDR_WIDTH-1:0] wb_adr_i;
output [31:0] wb_dat32_o;
input [3:0] ier;
input [3:0] iir;
input [1:0] fcr;
input [4:0] mcr;
input [7:0] lcr;
input [7:0] msr;
input [7:0] lsr;
input [`UART_FIFO_COUNTER_W-1:0] rf_count;
input [`UART_FIFO_COUNTER_W-1:0] tf_count;
input [2:0] tstate;
input [3:0] rstate;


wire [`UART_ADDR_WIDTH-1:0] wb_adr_i;
reg [31:0] wb_dat32_o;

always @(fcr or ier or iir or lcr or lsr or mcr or msr
 or rf_count or rstate or tf_count or tstate or wb_adr_i)
 case (wb_adr_i)

 5'b01000: wb_dat32_o = {msr,lcr,iir,ier,lsr};

 5'b01100: wb_dat32_o = {8'b0, fcr,mcr, rf_count, rstate, tf_count, tstate};
 default: wb_dat32_o = 0;
 endcase

endmodule

