package com.sd.lib.eos.rpc.params;

import com.sd.lib.eos.rpc.utils.RpcUtils;
import com.sd.lib.eos.rpc.utils.Utils;

/**
 * 转账
 */
public class TransferActionParams extends BaseParams<TransferActionParams.Args, TransferActionParams.Builder>
{
    private final Args args;

    private TransferActionParams(Builder builder)
    {
        super(builder);
        setAuthorizationActor(builder.from);
        this.args = new Args(builder);
    }

    @Override
    public final String getCode()
    {
        return "eosio.token";
    }

    @Override
    public final String getAction()
    {
        return "transfer";
    }

    @Override
    public final Args getArgs()
    {
        return this.args;
    }

    public static class Args extends BaseParams.Args<Builder>
    {
        private final String from;
        private final String to;
        private final String quantity;
        private final String memo;

        private Args(Builder builder)
        {
            super(builder);
            this.from = RpcUtils.checkAccountName(builder.from, "transfer from format error");
            this.to = RpcUtils.checkAccountName(builder.to, "transfer to format error");
            this.quantity = RpcUtils.checkMoney(builder.quantity, "transfer quantity was not specified");
            this.memo = builder.memo;
        }

        public String getFrom()
        {
            return from;
        }

        public String getTo()
        {
            return to;
        }

        public String getQuantity()
        {
            return quantity;
        }

        public String getMemo()
        {
            return memo;
        }
    }

    public static class Builder extends BaseParams.Builder<Builder>
    {
        private String from;
        private String to;
        private String quantity;
        private String memo;

        /**
         * 设置转账账号
         *
         * @param from
         * @return
         */
        public Builder setFrom(String from)
        {
            this.from = from;
            return this;
        }

        /**
         * 设置收款账号
         *
         * @param to
         * @return
         */
        public Builder setTo(String to)
        {
            this.to = to;
            return this;
        }

        /**
         * 设置转账金额
         *
         * @param quantity 数量
         * @param symbol   币种，默认EOS
         * @return
         */
        public Builder setQuantity(double quantity, String symbol)
        {
            if (Utils.isEmpty(symbol))
                symbol = "EOS";

            this.quantity = RpcUtils.formatMoney(quantity, symbol);
            return this;
        }

        /**
         * 设置备注
         *
         * @param memo
         * @return
         */
        public Builder setMemo(String memo)
        {
            this.memo = memo;
            return this;
        }

        public TransferActionParams build()
        {
            return new TransferActionParams(this);
        }
    }
}
