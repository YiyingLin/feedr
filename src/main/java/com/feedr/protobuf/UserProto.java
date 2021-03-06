// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: User.proto

package com.feedr.protobuf;

public final class UserProto {
  private UserProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface UserOrBuilder extends
      // @@protoc_insertion_point(interface_extends:User)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional string username = 1;</code>
     */
    java.lang.String getUsername();
    /**
     * <code>optional string username = 1;</code>
     */
    com.google.protobuf.ByteString
        getUsernameBytes();

    /**
     * <code>optional string password = 2;</code>
     */
    java.lang.String getPassword();
    /**
     * <code>optional string password = 2;</code>
     */
    com.google.protobuf.ByteString
        getPasswordBytes();

    /**
     * <code>optional string userType = 3;</code>
     */
    java.lang.String getUserType();
    /**
     * <code>optional string userType = 3;</code>
     */
    com.google.protobuf.ByteString
        getUserTypeBytes();
  }
  /**
   * Protobuf type {@code User}
   */
  public  static final class User extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:User)
      UserOrBuilder {
    // Use User.newBuilder() to construct.
    private User(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private User() {
      username_ = "";
      password_ = "";
      userType_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private User(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!input.skipField(tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();

              username_ = s;
              break;
            }
            case 18: {
              java.lang.String s = input.readStringRequireUtf8();

              password_ = s;
              break;
            }
            case 26: {
              java.lang.String s = input.readStringRequireUtf8();

              userType_ = s;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.feedr.protobuf.UserProto.internal_static_User_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.feedr.protobuf.UserProto.internal_static_User_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.feedr.protobuf.UserProto.User.class, com.feedr.protobuf.UserProto.User.Builder.class);
    }

    public static final int USERNAME_FIELD_NUMBER = 1;
    private volatile java.lang.Object username_;
    /**
     * <code>optional string username = 1;</code>
     */
    public java.lang.String getUsername() {
      java.lang.Object ref = username_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        username_ = s;
        return s;
      }
    }
    /**
     * <code>optional string username = 1;</code>
     */
    public com.google.protobuf.ByteString
        getUsernameBytes() {
      java.lang.Object ref = username_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        username_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int PASSWORD_FIELD_NUMBER = 2;
    private volatile java.lang.Object password_;
    /**
     * <code>optional string password = 2;</code>
     */
    public java.lang.String getPassword() {
      java.lang.Object ref = password_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        password_ = s;
        return s;
      }
    }
    /**
     * <code>optional string password = 2;</code>
     */
    public com.google.protobuf.ByteString
        getPasswordBytes() {
      java.lang.Object ref = password_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        password_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int USERTYPE_FIELD_NUMBER = 3;
    private volatile java.lang.Object userType_;
    /**
     * <code>optional string userType = 3;</code>
     */
    public java.lang.String getUserType() {
      java.lang.Object ref = userType_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        userType_ = s;
        return s;
      }
    }
    /**
     * <code>optional string userType = 3;</code>
     */
    public com.google.protobuf.ByteString
        getUserTypeBytes() {
      java.lang.Object ref = userType_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        userType_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!getUsernameBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, username_);
      }
      if (!getPasswordBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, password_);
      }
      if (!getUserTypeBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, userType_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getUsernameBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, username_);
      }
      if (!getPasswordBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, password_);
      }
      if (!getUserTypeBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, userType_);
      }
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.feedr.protobuf.UserProto.User)) {
        return super.equals(obj);
      }
      com.feedr.protobuf.UserProto.User other = (com.feedr.protobuf.UserProto.User) obj;

      boolean result = true;
      result = result && getUsername()
          .equals(other.getUsername());
      result = result && getPassword()
          .equals(other.getPassword());
      result = result && getUserType()
          .equals(other.getUserType());
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptorForType().hashCode();
      hash = (37 * hash) + USERNAME_FIELD_NUMBER;
      hash = (53 * hash) + getUsername().hashCode();
      hash = (37 * hash) + PASSWORD_FIELD_NUMBER;
      hash = (53 * hash) + getPassword().hashCode();
      hash = (37 * hash) + USERTYPE_FIELD_NUMBER;
      hash = (53 * hash) + getUserType().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.feedr.protobuf.UserProto.User parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.feedr.protobuf.UserProto.User parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.feedr.protobuf.UserProto.User parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.feedr.protobuf.UserProto.User parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.feedr.protobuf.UserProto.User parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.feedr.protobuf.UserProto.User parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.feedr.protobuf.UserProto.User parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.feedr.protobuf.UserProto.User parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.feedr.protobuf.UserProto.User parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.feedr.protobuf.UserProto.User parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.feedr.protobuf.UserProto.User prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code User}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:User)
        com.feedr.protobuf.UserProto.UserOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.feedr.protobuf.UserProto.internal_static_User_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.feedr.protobuf.UserProto.internal_static_User_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.feedr.protobuf.UserProto.User.class, com.feedr.protobuf.UserProto.User.Builder.class);
      }

      // Construct using com.feedr.protobuf.UserProto.User.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        username_ = "";

        password_ = "";

        userType_ = "";

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.feedr.protobuf.UserProto.internal_static_User_descriptor;
      }

      public com.feedr.protobuf.UserProto.User getDefaultInstanceForType() {
        return com.feedr.protobuf.UserProto.User.getDefaultInstance();
      }

      public com.feedr.protobuf.UserProto.User build() {
        com.feedr.protobuf.UserProto.User result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.feedr.protobuf.UserProto.User buildPartial() {
        com.feedr.protobuf.UserProto.User result = new com.feedr.protobuf.UserProto.User(this);
        result.username_ = username_;
        result.password_ = password_;
        result.userType_ = userType_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.feedr.protobuf.UserProto.User) {
          return mergeFrom((com.feedr.protobuf.UserProto.User)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.feedr.protobuf.UserProto.User other) {
        if (other == com.feedr.protobuf.UserProto.User.getDefaultInstance()) return this;
        if (!other.getUsername().isEmpty()) {
          username_ = other.username_;
          onChanged();
        }
        if (!other.getPassword().isEmpty()) {
          password_ = other.password_;
          onChanged();
        }
        if (!other.getUserType().isEmpty()) {
          userType_ = other.userType_;
          onChanged();
        }
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.feedr.protobuf.UserProto.User parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.feedr.protobuf.UserProto.User) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object username_ = "";
      /**
       * <code>optional string username = 1;</code>
       */
      public java.lang.String getUsername() {
        java.lang.Object ref = username_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          username_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string username = 1;</code>
       */
      public com.google.protobuf.ByteString
          getUsernameBytes() {
        java.lang.Object ref = username_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          username_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string username = 1;</code>
       */
      public Builder setUsername(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        username_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string username = 1;</code>
       */
      public Builder clearUsername() {
        
        username_ = getDefaultInstance().getUsername();
        onChanged();
        return this;
      }
      /**
       * <code>optional string username = 1;</code>
       */
      public Builder setUsernameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        username_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object password_ = "";
      /**
       * <code>optional string password = 2;</code>
       */
      public java.lang.String getPassword() {
        java.lang.Object ref = password_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          password_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string password = 2;</code>
       */
      public com.google.protobuf.ByteString
          getPasswordBytes() {
        java.lang.Object ref = password_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          password_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string password = 2;</code>
       */
      public Builder setPassword(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        password_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string password = 2;</code>
       */
      public Builder clearPassword() {
        
        password_ = getDefaultInstance().getPassword();
        onChanged();
        return this;
      }
      /**
       * <code>optional string password = 2;</code>
       */
      public Builder setPasswordBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        password_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object userType_ = "";
      /**
       * <code>optional string userType = 3;</code>
       */
      public java.lang.String getUserType() {
        java.lang.Object ref = userType_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          userType_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string userType = 3;</code>
       */
      public com.google.protobuf.ByteString
          getUserTypeBytes() {
        java.lang.Object ref = userType_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          userType_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string userType = 3;</code>
       */
      public Builder setUserType(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        userType_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string userType = 3;</code>
       */
      public Builder clearUserType() {
        
        userType_ = getDefaultInstance().getUserType();
        onChanged();
        return this;
      }
      /**
       * <code>optional string userType = 3;</code>
       */
      public Builder setUserTypeBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        userType_ = value;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:User)
    }

    // @@protoc_insertion_point(class_scope:User)
    private static final com.feedr.protobuf.UserProto.User DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.feedr.protobuf.UserProto.User();
    }

    public static com.feedr.protobuf.UserProto.User getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<User>
        PARSER = new com.google.protobuf.AbstractParser<User>() {
      public User parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new User(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<User> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<User> getParserForType() {
      return PARSER;
    }

    public com.feedr.protobuf.UserProto.User getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_User_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_User_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\nUser.proto\"<\n\004User\022\020\n\010username\030\001 \001(\t\022\020" +
      "\n\010password\030\002 \001(\t\022\020\n\010userType\030\003 \001(\tB\037\n\022co" +
      "m.feedr.protobufB\tUserProtob\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_User_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_User_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_User_descriptor,
        new java.lang.String[] { "Username", "Password", "UserType", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
