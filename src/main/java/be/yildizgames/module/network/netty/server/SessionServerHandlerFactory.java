/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2019 Grégory Van den Borre
 *
 *  More infos available: https://engine.yildiz-games.be
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without
 *  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do so,
 *  subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 *  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 */

package be.yildizgames.module.network.netty.server;

import be.yildizgames.module.network.DecoderEncoder;
import be.yildizgames.module.network.netty.HandlerFactory;
import be.yildizgames.module.network.server.SessionManager;
import io.netty.channel.ChannelHandler;

/**
 * @author Grégory Van den Borre
 */
public final class SessionServerHandlerFactory implements HandlerFactory {

    private final SessionServerHandler handler;

    private final DecoderEncoder codec;

    public SessionServerHandlerFactory(final SessionManager sessionManager, final DecoderEncoder codec) {
        super();
        this.codec = codec;
        this.handler = new SessionServerHandler(sessionManager);
    }

    @Override
    public ChannelHandler create() {
        if(this.codec == DecoderEncoder.WEBSOCKET) {
            return new SessionWebSocketMessageHandler(this.handler);
        }
        return new SessionMessageHandler(this.handler);
    }

    @Override
    public DecoderEncoder getCodec() {
        return this.codec;
    }

    @Override
    public boolean isServer() {
        return true;
    }
}
