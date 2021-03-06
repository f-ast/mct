`include "timescale.v"
`include "uart_defines.v"
`define UART_DL1 7 : 0
`define UART_DL2 15 : 8
module uart_regs (clk, wb_rst_i, wb_addr_i, wb_dat_i, wb_dat_o, wb_we_i, wb_re_i, modem_inputs, stx_pad_o, srx_pad_i, rts_pad_o, dtr_pad_o, int_o);
input clk;
input wb_rst_i;
input [`UART_ADDR_WIDTH - 1 : 0] wb_addr_i;
input [7 : 0] wb_dat_i;
output [7 : 0] wb_dat_o;
input wb_we_i;
input wb_re_i;
output stx_pad_o;
input srx_pad_i;
input [3 : 0] modem_inputs;
output rts_pad_o;
output dtr_pad_o;
output int_o;
wire [3 : 0] modem_inputs;
reg enable;
wire stx_pad_o;
wire srx_pad_i;
reg [7 : 0] wb_dat_o;
wire [`UART_ADDR_WIDTH - 1 : 0] wb_addr_i;
wire [7 : 0] wb_dat_i;
reg [3 : 0] ier;
reg [3 : 0] iir;
reg [1 : 0] fcr;
reg [4 : 0] mcr;
reg [7 : 0] lcr;
reg [7 : 0] msr;
reg [15 : 0] dl;
reg start_dlc;
reg lsr_mask_d;
reg msi_reset;
reg [15 : 0] dlc;
reg int_o;
reg [3 : 0] trigger_level;
reg rx_reset;
reg tx_reset;
wire dlab;
wire cts_pad_i, dsr_pad_i, ri_pad_i, dcd_pad_i;
wire loopback;
wire cts, dsr, ri, dcd;
wire rts_pad_o, dtr_pad_o;
wire [7 : 0] lsr;
wire lsr0, lsr1, lsr2, lsr3, lsr4, lsr5, lsr6, lsr7;
reg lsr0r, lsr1r, lsr2r, lsr3r, lsr4r, lsr5r, lsr6r, lsr7r;
wire lsr_mask;
assign lsr [7 : 0] = {lsr7r, lsr6r, lsr5r, lsr4r, lsr3r, lsr2r, lsr1r, lsr0r};
assign {cts_pad_i, dsr_pad_i, ri_pad_i, dcd_pad_i} = modem_inputs;
assign {cts, dsr, ri, dcd} = loopback ? {mcr [`UART_MC_RTS], mcr [`UART_MC_DTR], mcr [`UART_MC_OUT1], mcr [`UART_MC_OUT2]} : ~ {cts_pad_i, dsr_pad_i, ri_pad_i, dcd_pad_i};
assign dlab = lcr [`UART_LC_DL];
assign loopback = mcr [4];
assign rts_pad_o = mcr [`UART_MC_RTS];
assign dtr_pad_o = mcr [`UART_MC_DTR];
wire rls_int;
wire rda_int;
wire ti_int;
wire thre_int;
wire ms_int;
reg tf_push;
reg rf_pop;
wire [`UART_FIFO_REC_WIDTH - 1 : 0] rf_data_out;
wire rf_error_bit;
wire [`UART_FIFO_COUNTER_W - 1 : 0] rf_count;
wire [`UART_FIFO_COUNTER_W - 1 : 0] tf_count;
wire [2 : 0] state;
wire [9 : 0] counter_t;
wire [7 : 0] counter_b;
uart_transmitter
transmitter (clk, wb_rst_i, lcr, tf_push, wb_dat_i, enable, stx_pad_o, state, tf_count, tx_reset, lsr_mask);
uart_receiver
receiver (clk, wb_rst_i, lcr, rf_pop, srx_pad_i, enable, rda_int, counter_t, counter_b, rf_count, rf_data_out, rf_error_bit, rf_overrun, rx_reset, lsr_mask);
always @ (posedge clk or posedge wb_rst_i) begin
    if (wb_rst_i)
        begin
            wb_dat_o <= # 1 8?b0;
        end
    else
        if (wb_re_i)
            case (wb_addr_i) `UART_REG_RB : if (dlab)
                wb_dat_o <= # 1 dl [`UART_DL1];
            else
                wb_dat_o <= # 1 rf_data_out [9 : 2];

            `UART_REG_IE
            : wb_dat_o <= # 1 dlab ? dl [`UART_DL2] : ier;
            `UART_REG_II
            : wb_dat_o <= # 1 {4?b1100, iir};
            `UART_REG_LC
            : wb_dat_o <= # 1 lcr;
            `UART_REG_LS
            : wb_dat_o <= # 1 lsr;
            `UART_REG_MS
            : wb_dat_o <= # 1 msr;
            default : wb_dat_o <= # 1 8?b0;
            endcase
        else
            wb_dat_o <= # 1 8?b0;

end

always @ (posedge clk or posedge wb_rst_i) begin
    if (wb_rst_i)
        rf_pop <= # 1 0;
    else
        if (rf_pop)
            rf_pop <= # 1 0;
        else
            if (wb_re_i && wb_addr_i == `UART_REG_RB && ! dlab)
                rf_pop <= # 1 1;

end

wire lsr_mask_condition;
wire iir_read;
wire msr_read;
wire fifo_read;
wire thre_write;
assign lsr_mask_condition = (wb_re_i && wb_addr_i == `UART_REG_LS && ! dlab);
assign iir_read = (wb_re_i && wb_addr_i == `UART_REG_II && ! dlab);
assign msr_read = (wb_re_i && wb_addr_i == `UART_REG_MS && ! dlab);
assign fifo_read = (wb_re_i && wb_addr_i == `UART_REG_RB && ! dlab);
assign thre_write = (wb_we_i && wb_addr_i == `UART_REG_TR && ! dlab);
always @ (posedge clk or posedge wb_rst_i) begin
    if (wb_rst_i)
        lsr_mask_d <= # 1 0;
    else
        lsr_mask_d <= # 1 lsr_mask_condition;

end

assign lsr_mask = lsr_mask_condition && ~ lsr_mask_d;
always @ (posedge clk or posedge wb_rst_i) begin
    if (wb_rst_i)
        msi_reset <= # 1 0;
    else
        if (msi_reset)
            msi_reset <= # 1 0;
        else
            if (wb_re_i && wb_addr_i == `UART_REG_MS)
                msi_reset <= # 1 1;

end

always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    lcr <= # 1 8?b00000011;
else
    if (wb_we_i && wb_addr_i == `UART_REG_LC)
        lcr <= # 1 wb_dat_i;

always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    begin
        ier <= # 1 4?b0000;
        dl [`UART_DL2] <= # 1 8?b0;
    end
else
    if (wb_we_i && wb_addr_i == `UART_REG_IE)
        if (dlab)
            begin
                dl [`UART_DL2] <= # 1 wb_dat_i;
            end
        else
            ier <= # 1 wb_dat_i [3 : 0];

always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    begin
        fcr <= # 1 2?b11;
        rx_reset <= # 1 0;
        tx_reset <= # 1 0;
    end
else
    if (wb_we_i && wb_addr_i == `UART_REG_FC)
        begin
            fcr <= # 1 wb_dat_i [7 : 6];
            rx_reset <= # 1 wb_dat_i [1];
            tx_reset <= # 1 wb_dat_i [2];
        end
    else
        begin
            rx_reset <= # 1 0;
            tx_reset <= # 1 0;
        end

always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    mcr <= # 1 5?b0;
else
    if (wb_we_i && wb_addr_i == `UART_REG_MC)
        mcr <= # 1 wb_dat_i [4 : 0];

always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    begin
        dl [`UART_DL1] <= # 1 8?b0;
        tf_push <= # 1 1?b0;
        start_dlc <= # 1 1?b0;
    end
else
    if (wb_we_i && wb_addr_i == `UART_REG_TR)
        if (dlab)
            begin
                dl [`UART_DL1] <= # 1 wb_dat_i;
                start_dlc <= # 1 1?b1;
                tf_push <= # 1 1?b0;
            end
        else
            begin
                tf_push <= # 1 1?b1;
                start_dlc <= # 1 1?b0;
            end

    else
        begin
            start_dlc <= # 1 1?b0;
            tf_push <= # 1 1?b0;
        end

always @ (fcr) case (fcr [`UART_FC_TL]) 2?b00 : trigger_level = 1;
2?b01 : trigger_level = 4;
2?b10 : trigger_level = 8;
2?b11 : trigger_level = 14;
endcase

always @ (posedge clk or posedge wb_rst_i) begin
    if (wb_rst_i)
        msr <= # 1 0;
    else
        begin
            msr [`UART_MS_DDCD : `UART_MS_DCTS] <= # 1 msi_reset ? 4?b0 : msr [`UART_MS_DDCD : `UART_MS_DCTS]
            | ({dcd, ri, dsr, cts} ^ msr [`UART_MS_CDCD : `UART_MS_CCTS]);
            msr [`UART_MS_CDCD : `UART_MS_CCTS] <= # 1 {dcd, ri, dsr, cts};
        end

end

assign lsr0 = (rf_count != 4?b0);
assign lsr1 = rf_overrun;
assign lsr2 = rf_data_out [1];
assign lsr3 = rf_data_out [0];
assign lsr4 = (counter_b == 8?b0);
assign lsr5 = (tf_count == 5?b0);
assign lsr6 = (tf_count == 5?b0 && (state == 0));
assign lsr7 = rf_error_bit;
always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    lsr0r <= # 1 0;
else
    lsr0r <= # 1 lsr0;

reg lsr1_d;
always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    lsr1_d <= # 1 0;
else
    lsr1_d <= # 1 lsr1;

always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    lsr1_d <= # 1 0;
else
    lsr1r <= # 1 lsr_mask ? 0 : lsr1 && ~ lsr1_d;

reg lsr2_d;
always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    lsr2_d <= # 1 0;
else
    lsr2_d <= # 1 lsr2;

always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    lsr2_d <= # 1 0;
else
    lsr2r <= # 1 lsr_mask ? 0 : lsr2 && ~ lsr2_d;

reg lsr3_d;
always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    lsr3_d <= # 1 0;
else
    lsr3_d <= # 1 lsr3;

always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    lsr3_d <= # 1 0;
else
    lsr3r <= # 1 lsr_mask ? 0 : lsr3 && ~ lsr3_d;

reg lsr4_d;
always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    lsr4_d <= # 1 0;
else
    lsr4_d <= # 1 lsr4;

always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    lsr4_d <= # 1 0;
else
    lsr4r <= # 1 lsr_mask ? 0 : lsr4 && ~ lsr4_d;

reg lsr5_d;
wire tx_fifo_write;
assign tx_fifo_write = (wb_we_i && ~ dlab && wb_addr_i == `UART_REG_TR);
always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    lsr5_d <= # 1 0;
else
    lsr5_d <= # 1 lsr5;

always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    lsr5_d <= # 1 0;
else
    lsr5r <= # 1 (lsr_mask || iir_read || tx_fifo_write) ? 0 : lsr5 && ~ lsr5_d;

reg lsr6_d;
always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    lsr6_d <= # 1 0;
else
    lsr6_d <= # 1 lsr6;

always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    lsr6_d <= # 1 0;
else
    lsr6r <= # 1 (lsr_mask || tx_fifo_write) ? 0 : lsr6 && ~ lsr6_d;

reg lsr7_d;
always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    lsr7_d <= # 1 0;
else
    lsr7_d <= # 1 lsr7;

always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    lsr7_d <= # 1 0;
else
    lsr7r <= # 1 lsr_mask ? 0 : lsr7 && ~ lsr7_d;

always @ (posedge clk or posedge wb_rst_i) begin
    if (wb_rst_i)
        dlc <= # 1 0;
    else
        if (start_dlc | ~ (| dlc))
            dlc <= # 1 dl - 1;
        else
            dlc <= # 1 dlc - 1;

end

always @ (posedge clk or posedge wb_rst_i) begin
    if (wb_rst_i)
        enable <= # 1 1?b0;
    else
        if (| dl & ~ (| dlc))
            enable <= # 1 1?b1;
        else
            enable <= # 1 1?b0;

end

assign rls_int = ier [`UART_IE_RLS] && (lsr [`UART_LS_OE] || lsr [`UART_LS_PE] || lsr [`UART_LS_FE] || lsr [`UART_LS_BI]);
assign rda_int = ier [`UART_IE_RDA] && (rf_count >= {1?b0, trigger_level});
assign thre_int = ier [`UART_IE_THRE] && lsr [`UART_LS_TFE];
assign ms_int = ier [`UART_IE_MS] && (| msr [3 : 0]);
assign ti_int = ier [`UART_IE_RDA] && (counter_t == 10?b0);
reg rls_int_d;
reg thre_int_d;
reg ms_int_d;
reg ti_int_d;
always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    rls_int_d <= # 1 0;
else
    rls_int_d <= # 1 rls_int;

always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    thre_int_d <= # 1 0;
else
    thre_int_d <= # 1 thre_int;

always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    ms_int_d <= # 1 0;
else
    ms_int_d <= # 1 ms_int;

always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    ti_int_d <= # 1 0;
else
    ti_int_d <= # 1 ti_int;

wire rls_int_rise;
wire thre_int_rise;
wire ms_int_rise;
wire ti_int_rise;
assign rls_int_rise = rls_int & ~ rls_int_d;
assign thre_int_rise = thre_int & ~ thre_int_d;
assign ms_int_rise = ms_int & ~ ms_int_d;
assign ti_int_rise = ti_int & ~ ti_int_d;
reg rls_int_pnd;
reg thre_int_pnd;
reg ms_int_pnd;
reg ti_int_pnd;
always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    rls_int_pnd <= # 1 0;
else
    rls_int_pnd <= # 1 lsr_mask ? 0 : rls_int_rise ? 1 : rls_int_pnd && ier [`UART_IE_RLS];

always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    thre_int_pnd <= # 1 0;
else
    thre_int_pnd <= # 1 thre_write || iir_read ? 0 : thre_int_rise ? 1 : thre_int_pnd && ier [`UART_IE_THRE];

always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    ms_int_pnd <= # 1 0;
else
    ms_int_pnd <= # 1 msr_read ? 0 : ms_int_rise ? 1 : ms_int_pnd && ier [`UART_IE_MS];

always @ (posedge clk or posedge wb_rst_i) if (wb_rst_i)
    ms_int_pnd <= # 1 0;
else
    ti_int_pnd <= # 1 fifo_read ? 0 : ti_int_rise ? 1 : ti_int_pnd && ier [`UART_IE_RDA];

always @ (posedge clk or posedge wb_rst_i) begin
    if (wb_rst_i)
        int_o <= # 1 1?b0;
    else
        int_o <= # 1 rls_int_pnd ? ~ lsr_mask : rda_int ? 1 : ti_int_pnd ? ~ fifo_read : thre_int_pnd ? ! (thre_write & iir_read) : ms_int_pnd ? ~ msr_read : 0;

end

always @ (posedge clk or posedge wb_rst_i) begin
    if (wb_rst_i)
        iir <= # 1 1;
    else
        if (rls_int_pnd)
            begin
                iir [`UART_II_II] <= # 1 `UART_II_RLS;
                iir [`UART_II_IP] <= # 1 1?b0;
            end
        else
            if (rda_int)
                begin
                    iir [`UART_II_II] <= # 1 `UART_II_RDA;
                    iir [`UART_II_IP] <= # 1 1?b0;
                end
            else
                if (ti_int_pnd)
                    begin
                        iir [`UART_II_II] <= # 1 `UART_II_TI;
                        iir [`UART_II_IP] <= # 1 1?b0;
                    end
                else
                    if (thre_int_pnd)
                        begin
                            iir [`UART_II_II] <= # 1 `UART_II_THRE;
                            iir [`UART_II_IP] <= # 1 1?b0;
                        end
                    else
                        if (ms_int_pnd)
                            begin
                                iir [`UART_II_II] <= # 1 `UART_II_MS;
                                iir [`UART_II_IP] <= # 1 1?b0;
                            end
                        else
                            begin
                                iir [`UART_II_II] <= # 1 0;
                                iir [`UART_II_IP] <= # 1 1?b1;
                            end

end

endmodule
